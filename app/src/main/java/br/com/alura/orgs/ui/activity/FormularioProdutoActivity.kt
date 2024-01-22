package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.model.Produto
import java.math.BigDecimal
import kotlin.math.log

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val botaoSalvar = findViewById<Button>(R.id.botao_salvar)
        botaoSalvar.setOnClickListener {
            adicionarProduto()
            finish()
        }
    }

    fun adicionarProduto() {
        val campoNome = findViewById<EditText>(R.id.nome)
        val nome = campoNome.text.toString()
        Log.i("FormularioProduto", "onCreate: $nome")

        val campoDescricao = findViewById<EditText>(R.id.descricao)
        val descricao = campoDescricao.text.toString()

        val campoValor = findViewById<EditText>(R.id.valor)
        val valorTexto = campoValor.text.toString()
        val valor = if(valorTexto.isBlank()){
            BigDecimal.ZERO
        }
        else{
            BigDecimal(valorTexto)
        }

        val produtoNovo = Produto(
            nome = nome,
            descricao = descricao,
            valor = valor
        )

        Log.i("FormlularioProduto", "onCreate: $produtoNovo")
        val produtosDao = ProdutosDao()
        produtosDao.adicionar(produtoNovo)
        Log.i("FormlularioProduto", "onCreate: ${produtosDao.buscarTodos()}")
    }

}