package com.example.gofood.ui.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gofood.R
import com.example.gofood.databinding.FragmentAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAddBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        databaseReference= FirebaseDatabase.getInstance().getReference("Dishes")
        auth = FirebaseAuth.getInstance()
        binding.submitBtn.setOnClickListener {
            val title = binding.dishAdd.text.toString()
            val description = binding.descriptionAdd.text.toString()
            val item = binding.itemAdd.text.toString()
            val time = binding.timeAdd.text.toString()
            val catagory = binding.catagoryAdd.text.toString()
            val image = binding.imageAdd.contentDescription.toString()

            if (title.isEmpty()&& description.isEmpty()&&item.isEmpty()&& time.isEmpty()&& catagory.isEmpty()&&image.isEmpty()){
                Toast.makeText(requireContext(),"fill the box", Toast.LENGTH_SHORT).show()
            }else {
                val currentUser = auth.currentUser
                currentUser?.let { user ->
                    val dishId = databaseReference.child("user").child(user.uid).child("dish").push().key
                    val dish = DishItem(title, description, item, time, catagory,image.toInt())
                    if (dishId != null)
                        databaseReference.child("user").child(user.uid).child("dish").child(dishId!!).setValue(dishId)
                            .addOnCompleteListener{ task ->
                                if (task.isSuccessful){
                                    Toast.makeText(requireContext(),"submit your dish", Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(requireContext(), "Please sign in to add a dish", Toast.LENGTH_SHORT).show()
                                }
                            }

                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}