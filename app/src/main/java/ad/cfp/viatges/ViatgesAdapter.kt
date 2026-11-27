package ad.cfp.viatges

import ad.cfp.viatges.databinding.ViatgeItemBinding
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ViatgesAdapter(
    val llista: List<Viatge>,
    val listener: ViatgesAdapterActions
) : RecyclerView.Adapter<ViatgesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(llista[position], listener)
    }

    override fun getItemCount(): Int {
        return llista.size
    }

    class ViewHolder(val binding: ViatgeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viatge: Viatge, listener: ViatgesAdapterActions) {
            if (viatge.imatge != "") {
                Glide.with(binding.root.context)
                    .load(viatge.imatge)
                    .thumbnail(
                        Glide.with(binding.root.context)
                            .load(R.drawable.loading_gif)
                    )
                    .error(
                        Glide.with(binding.root.context)
                            .load(R.drawable.error_gif)
                    )
                    .into(binding.imatge)

            }

            binding.preu.text = "${viatge.preu} €"
            binding.destinacio.text = viatge.destinacio
            binding.dataSortida.text = viatge.dataSortida
            binding.dataTornada.text = viatge.dataTornada

           val color =  if(viatge.preferit) R.color.favorit
                else R.color.black
               binding.favorit.setIconTintResource(color)



            binding.root.setOnClickListener {
                listener.click(viatge)

            }

            binding.delate.setOnClickListener {
                listener.delate(viatge.id, bindingAdapterPosition)
            }

            binding.favorit.setOnClickListener {
                listener.favorite(viatge.id, bindingAdapterPosition)
            }


        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViatgeItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}

interface ViatgesAdapterActions {
    fun click(viatge: Viatge)
    fun delate(id: Int, pos: Int)
    fun favorite(id: Int, pos: Int)
    fun add(viatge: Viatge)
}



