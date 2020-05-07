<template>
  <tr>
    <th scope="row">{{ pokemon.id }}</th>
    <td>{{ pokemon.nome }}</td>
    <td><img v-bind:src="pokemon.img" v-bind:alt="`Imagem do pokemon ${pokemon.nome}`"></td>
    <td>{{ pokemon.altura }} cm</td>
    <td>{{ pokemon.peso }} kg</td>
  </tr>
</template>

<script>
export default {
  name: 'PokemonLinha',
  props: {
    pokemon: Object
  }
}
</script>
