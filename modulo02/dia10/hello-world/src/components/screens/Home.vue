<template>
  <section class="container">
    <h1>{{ usuario }}, Bem vindo!</h1>
    <div class="form-group row">
      <label for="type-input" class="col-form-label">Selecione o tipo:</label>
      <select id="type-input" v-model="opcaoSelecionada" v-on:change="onChange" class="form-control">
        <option v-for="opcao in opcoes" v-bind:value="opcao" v-bind:key="opcao.id">
          {{ opcao.texto }}
        </option>
      </select>
    </div>
    <h3 v-show="opcaoSelecionada.texto" >Selecionado: {{ opcaoSelecionada.texto }}</h3>
    
    <div class="form-group row">
      <label for="number-input" class="col-form-label">Quantidade por página:</label>
      <input class="form-control" type="number" id="number-input" v-model="qtdPokemonsPagina">
    </div>

    <div v-show="opcaoSelecionada.texto" v-infinite-scroll="infiniteScrollfunc" infinite-scroll-disabled="busy" infinite-scroll-distance="4" v-bind:infinite-scroll-immediate-check="true">

      <TabelaPokemon v-bind:lista="listaPokemons" />
      <Button v-bind:texto="textoBtnProximo" v-bind:click="carregarMaisPokemons" />

    </div>
  </section>
</template>

<script>
import Button from '../shared/Button.vue'
import PokeApi from '../../api/pokeApi.js'
import TabelaPokemon from '../shared/TabelaPokemon.vue'

export default {
  name: 'Home',
  components: {
    TabelaPokemon, Button
  },
  data: () => {
    return {
      usuario: '',
      opcaoSelecionada: {},
      opcoes: [
        { id: 1, texto: 'Normal' },
        { id: 2, texto: 'Lutador' },
        { id: 3, texto: 'Voador' }
      ],
      paginaAtual: 1,
      qtdPokemonsPagina: 10,
      listaPokemons: [],
      textoBtnProximo: 'Mostrar mais',
      busy: false
    }
  },
  methods: {
    infiniteScrollfunc() {
      console.log( "infiniteScrollfunc")

      this.busy = true
      this.carregarMaisPokemons()

      setTimeout(() => {
        this.busy = false
      }, 2000);
    },
    carregarMaisPokemons() {
      console.log( "Carregar Mais")
      this.paginaAtual += 1
      this.textoBtnProximo = "Carregando..."
      setTimeout( () => {
        this.buscar()
      }, 1000)
      setTimeout( () => {
        this.textoBtnProximo = 'Mostrar mais' 
      }, 2000)
    },
    onChange() {
      this.listaPokemons = []
      this.paginaAtual = 1
      this.buscar()
    },
    async buscar() {
      const pokeApi = new PokeApi()
      let novosPokemons = await pokeApi.listarPorTipo( this.opcaoSelecionada.id, this.qtdPokemonsPagina, this.paginaAtual )

      this.listaPokemons = [...this.listaPokemons, ...novosPokemons]
    }
  },
  created() {
    this.usuario = this.$route.params.usuario
  }
}
</script>
