package ad.cfp.viatges

import ad.cfp.viatges.databinding.ActivityMainBinding
import ad.cfp.viatges.ui.DetallViatgeActivity
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), ViatgesAdapterActions {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viatges: MutableList<Viatge>
    private lateinit var viatgesAdapter: ViatgesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viatges = mutableListOf(
            Viatge(1, "París", "2026-11-01", "2026-11-05", 450.0, "https://media.traveler.es/photos/691b9bcff4c4548cab3a05b4/16:9/w_1920,c_limit/1072749124.jpg"),
            Viatge(2, "Roma", "2026-11-10", "2026-11-14", 380.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Colosseum_Rome_.jpg"),
            Viatge(3, "Londres", "2026-12-01", "2026-12-06", 520.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Big_Ben_London_2025.jpg"),
            Viatge(4, "Tòquio", "2027-01-15", "2027-01-25", 1850.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Five-storied_Pagoda_and_Tokyo_Skytree.jpg"),
            Viatge(5, "Nova York", "2027-02-05", "2027-02-12", 1250.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Statue_of_Liberty,_NY.jpg"),
            Viatge(6, "Barcelona", "2027-03-10", "2027-03-13", 220.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Sagrada_Família,_Barcelona.jpg"),
            Viatge(7, "Lisboa", "2027-04-01", "2027-04-05", 310.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Yellow_tram_in_Lisbon,_Portugal.jpg"),
            Viatge(8, "Reykjavík", "2027-05-15", "2027-05-22", 980.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Iceland-Reykjavik-Hallgrimskirkja-1.jpg"),
            Viatge(9, "Atenes", "2027-06-10", "2027-06-17", 640.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Acropolis_Athens_Greece.jpg"),
            Viatge(10, "Bali", "2027-07-01", "2027-07-12", 1490.0, "https://commons.wikimedia.org/wiki/Special:FilePath/Ulun_Danu_Beratan_Temple.jpg")
        )

        viatgesAdapter = ViatgesAdapter(viatges, this)
        binding.rv.apply {
            adapter = viatgesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun click(viatge: Viatge) {
        Snackbar
            .make(
                binding.root,
                viatge.destinacio,
                Snackbar.LENGTH_SHORT
            )
            .show()


        val intent = Intent(
            this, DetallViatgeActivity::class.java)
        intent.putExtra("viatge", viatge)
        startActivity (intent)


    }

    override fun delate(id: Int, pos: Int) {
        viatges.removeIf { viatge -> viatge.id ==id }
        viatgesAdapter.notifyItemRemoved(pos)

    }

    override fun favorite(id: Int, pos: Int) {
        val viatge = viatges.find { viatge -> viatge.id ==id }!!
        val listPos = viatges.lastIndexOf(viatge)
        viatge.preferit = !viatge.preferit
        viatges[listPos] = viatge
        viatgesAdapter.notifyItemChanged(pos)
    }

    override fun add(viatge: Viatge) {
        val contracte = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){resultat ->
        if (resultat.resultCode == RESULT_OK)

        }
    }
}