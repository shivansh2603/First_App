package com.example.loginpage.screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.loginpage.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DEPRECATION")
class ProfileScreen : Fragment() {

    private lateinit var cameraIcon: ImageView
    private lateinit var capturedImageView: ImageView
    private lateinit var galleryIcon: ImageView
    private val CAMERA_PERMISSION_REQUEST = 100
    private val READ_MEDIA_REQUEST = 101
    private var currentPhotoPath: String? = null
    private val READ_EXTERNAL_STORAGE_REQUEST = 102

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = Uri.fromFile(currentPhotoPath?.let { File(it) })
                capturedImageView.setImageURI(imageUri)
            }
        }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        cameraIcon = view.findViewById(R.id.camera_icon)
        capturedImageView = view.findViewById(R.id.captured_image)
        galleryIcon = view.findViewById(R.id.gallery_icon)

        cameraIcon.setOnClickListener {
            checkCameraPermission()
        }

        galleryIcon.setOnClickListener {
            Log.d("ProfileScreen", "Gallery icon clicked")
            checkGalleryPermission()
        }

        return view
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestCameraPermission()
        } else {
            takePicture()
        }
    }

    private fun checkGalleryPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            // Android 13 and higher
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_MEDIA_VIDEO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestGalleryPermission()
            } else {
                openGallery()
            }
        } else {
            // Older Android versions
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestStoragePermission()
            } else {
                openGallery()
            }
        }
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_EXTERNAL_STORAGE_REQUEST
        )
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST
        )
    }

    private fun requestGalleryPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            ),
            READ_MEDIA_REQUEST
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture()
                } else {
                    showPermissionDeniedMessage(Manifest.permission.CAMERA)
                }
            }

            READ_EXTERNAL_STORAGE_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    showPermissionDeniedMessage(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }


    private fun showPermissionDeniedMessage(permission: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission)) {
            val message = when (permission) {
                Manifest.permission.CAMERA -> "Camera permission is required to take a picture."
                Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO -> "Access to photos and videos is required to choose a profile picture."
                else -> "Permission is required."
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            if (permission == Manifest.permission.CAMERA) {
                requestCameraPermission()
            } else if (permission == Manifest.permission.READ_MEDIA_IMAGES || permission == Manifest.permission.READ_MEDIA_VIDEO) {
                requestGalleryPermission()
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Permission denied permanently. You can enable it in app settings.",
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", requireContext().packageName, null)
            intent.data = uri
            startActivity(intent)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            val photoFile: File? = try {
                createImageFile()
            } catch (ex: IOException) {
                Log.e("ProfileScreen", "Error creating image file", ex)
                Toast.makeText(requireContext(), "Error creating image file", Toast.LENGTH_SHORT)
                    .show()
                null
            }
            photoFile?.also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    requireContext(),
                    "com.example.loginpage.fileprovider", // Replace with your FileProvider authority
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                takePictureLauncher.launch(takePictureIntent)
            }
        }
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private val openGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                result.data?.data?.let { imageUri ->
                    capturedImageView.setImageURI(imageUri)
                }
            }
        }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        openGalleryLauncher.launch(intent)
    }
}