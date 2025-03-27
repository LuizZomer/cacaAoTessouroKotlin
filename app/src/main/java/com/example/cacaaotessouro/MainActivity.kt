package com.example.cacaaotessouro

import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cacaaotessouro.ui.theme.CacaAoTessouroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var erro by remember { mutableIntStateOf(0) }
            var success by remember { mutableIntStateOf(0) }
            val navigationController = rememberNavController()
            NavHost(
                navController = navigationController,
                startDestination = "/tela1",
            ) {

                composable("/tela1") {
                    Home(
                        clickB1 = { navigationController.navigate("/tela2") },
                    )
                }

                composable("/tela2") {
                    Tela(
                        "Quantos é 2 + 2?", clickB1 = { erro = 1 },
                        clickB2 = {
                            erro = 0
                            success = 1
                        },
                        clickB3 = { erro = 1 },
                        clickB4 = {
                            erro = 0
                            success = 0
                            navigationController.navigate("/tela3")
                        },
                        clickB5 = {
                            success = 0
                            erro = 0
                            navigationController.navigate("/tela1")
                        },
                        botao1Text = "5",
                        botao2Text = "4",
                        botao3Text = "3",
                        erro = erro,
                        success = success
                    )
                }

                composable("/tela3") {
                    Tela(
                        "Quantos é 10 + 10?", clickB1 = { erro = 1 },
                        clickB2 = {
                            success = 1
                            erro = 0
                        },
                        clickB3 = { erro = 1 },
                        botao1Text = "10",
                        botao2Text = "20",
                        botao3Text = "70",
                        clickB4 = {
                            success = 0
                            erro = 0
                            navigationController.navigate("/tela4")
                        },
                        clickB5 = {
                            success = 0
                            navigationController.navigate("/tela2")
                        },
                        erro = erro,
                        success = success
                    )
                }

                composable("/tela4") {
                    Tela(
                        "Quantos é 20 + 20?", clickB1 = { erro = 1 },
                        clickB2 = {
                            erro = 0
                            success = 1
                        },
                        clickB3 = { erro = 1 },
                        clickB4 = {
                            success = 0
                            navigationController.navigate("/tela5")
                        },
                        clickB5 = {
                            erro = 0
                            success = 0
                            navigationController.navigate("/tela3")
                        },

                        botao1Text = "60",
                        botao2Text = "40",
                        botao3Text = "80",
                        erro = erro,
                        success = success
                    )
                }

                composable("/tela5") {
                    Congratulations(
                        onClick = {
                            navigationController.navigate("/tela1") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Tela(
    nomeTela: String,
    clickB1: () -> Unit,
    clickB2: () -> Unit,
    clickB3: () -> Unit,
    clickB4: () -> Unit,
    clickB5: () -> Unit,
    botao1Text: String,
    botao2Text: String,
    botao3Text: String,
    erro: Int,
    success: Int
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(nomeTela)
        if (success == 0) {
            Button(onClick = clickB1) {
                Text(text = botao1Text)
            }
            Button(onClick = clickB2) {
                Text(text = botao2Text)
            }
            Button(onClick = clickB3) {
                Text(text = botao3Text)
            }
        }

        if (erro == 1) {
            Text("Você errou!, tente outra vez")
        }

        if (success == 1) {
            Text("Você acertou!")
            Button(onClick = clickB4) {
                Text(text = "Proxima fase")
            }
            Button(onClick = clickB5) {
                Text(text = "Voltar")
            }
        }
    }
}

@Composable
fun Home(
    clickB1: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = clickB1) {
            Text(text = "Inicie a caça ao tesouro")
        }
    }
}

@Composable
fun Congratulations(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Parabéns, você achou o tesouro")
        Button(onClick = onClick) {
            Text("Voltar para home!")
        }
    }

}

