<template>
  <div class="table-responsive">
    <table class="table table-hover">
      <thead class="thead-dark">
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Nome</th>
          <th scope="col">Imagem</th>
          <th scope="col">Altura</th>
          <th scope="col">Peso</th>
        </tr>
      </thead>
      <tbody>
        <PokemonLinha v-for="poke in lista" v-bind:key="poke.id" v-bind:pokemon="poke" />
      </tbody>
    </table>
  </div>
</template>

<script>
import PokemonLinha from '../shared/PokemonLinha.vue'

export default {
  name: 'TabelaPokemon',
  components: {
    PokemonLinha
  },
  props: {
    lista: Array
  }
}
</script>
