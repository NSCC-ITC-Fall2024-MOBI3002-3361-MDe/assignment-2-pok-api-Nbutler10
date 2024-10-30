package com.example.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.pokemonapp.model.PokemonResponse
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokemonapp.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonApp() // Call Composable Function
        }
    }

    @Composable
    fun PokemonApp() {
        val viewModel: PokemonViewModel = viewModel()
        var pokemonName by remember { mutableStateOf("") }
        var pokemonData by remember { mutableStateOf<PokemonResponse?>(null) }

        // Collect the pokemonInfo from the ViewModel (PokemonViewModel)
        LaunchedEffect(Unit) {
            viewModel.pokemonInfo.collect {
                data -> pokemonData = data
            }
        }

        Column( // Formating
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Arrange from the top
        ) {
            // Pokémon Logo at top
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo), // In drawable folder
                contentDescription = null,
                modifier = Modifier.size(300.dp) // Adjust size as needed
            )

            // Row to align TextField and the "Go" Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between each
            ) {
                TextField(
                    value = pokemonName,
                    onValueChange = { pokemonName = it },
                    label = { Text("Enter Pokémon Name") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )
                Button(onClick = {
                    viewModel.fetchPokemon(pokemonName)
                }) {
                    Text("Go")
                }
            }

            // Formating // Spacer to push the rest of the content to the middle of the screen
            Spacer(modifier = Modifier.height(5.dp))

            // Pokémon info (list at bottom)
            pokemonData?.let {
                // Pokémon Image
                Image(
                    painter = rememberAsyncImagePainter(it.sprites.front_default),
                    contentDescription = null,
                    modifier = Modifier.size(205.dp)
                )
                // Name
                Text(
                    "${it.name}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 35.sp) // Formating
                ) // Type - single or plural
                Text(
                    "Type(s): ${it.types.joinToString { type -> type.type.name }}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp)
                ) // Weight
                Text(
                    "Weight: ${it.weight}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
                )
                Text( // Height
                    "Height: ${it.height}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
                )
            } ?: run {
                Text(
                    "No Pokémon data available or an error occurred.",
                    style = MaterialTheme.typography.bodyLarge // Start as soon as app starts,
                                                              // will remove once you enter a name
                )
            }
        }
    }
}