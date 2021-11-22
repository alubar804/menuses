package com.example.myapplication.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.Places
import com.example.myapplication.PlacesAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val items = ArrayList<Places>()
        items.add(Places(R.drawable.image1,"Card 1"))
        items.add(Places(R.drawable.image2,"Card 2"))
        items.add(Places(R.drawable.image3,"Card 3"))
        items.add(Places(R.drawable.image4,"Card 4"))
        items.add(Places(R.drawable.image5,"Card 5"))
        items.add(Places(R.drawable.image6,"Card 6"))
        items.add(Places(R.drawable.image7,"Card 7"))
        items.add(Places(R.drawable.image8,"Card 8"))
        items.add(Places(R.drawable.image9,"Card 9"))

        val recView = binding.recicler
        val adaptador = PlacesAdapter(items)
        recView.setHasFixedSize(true)
        recView.itemAnimator = DefaultItemAnimator()


        recView.adapter = adaptador
        recView.layoutManager= StaggeredGridLayoutManager(2,1)

        var modeCallBack: ActionMode.Callback = object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                val id = item?.itemId
                when (id) {
                    R.id.action_editar -> {
                        Log.i("MainActivity", "editar")
                        mode?.finish()
                    }
                    R.id.action_eliminar -> {
                        Log.i("MainActivity", "eliminar")
                        mode?.finish()
                    }
                    R.id.action_compartir -> {
                        Log.i("MainActivity", "compartir")
                        mode?.finish()
                    }
                    else -> return false
                }
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                var mode = mode
                mode = null
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                mode.setTitle("Options")
                mode.getMenuInflater().inflate(R.menu.menu_context, menu)
                return true
            }
        }

        adaptador.onLongClick = { view ->
            view.startActionMode(modeCallBack)
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}