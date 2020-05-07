<template>
  <form v-on:submit.prevent="onSubmit" class="container">
    <EmailInput nome="usuario" ref="usuarioRef"/>
    <PasswordInput nome="senha" ref="senhaRef"/>
    <div v-show="bag.has( 'auth' )" class="alert alert-danger" role="alert">
      <span>{{ bag.first( 'auth' ) }}</span>
    </div>
    <Button v-bind:texto="textoBotao" v-bind:click="noop" type="submit" />
  </form>
</template>

<script>
import Button from '../shared/Button.vue'
import EmailInput from '../shared/EmailInput.vue'
import PasswordInput from '../shared/PasswordInput.vue'
import { ErrorBag } from 'vee-validate'

export default {
  name: 'Login',
  components: {
    Button, EmailInput, PasswordInput
  },
  data: () => {
    return {
      textoBotao: 'Entrar',
      bag: new ErrorBag()
    }
  },
  methods: {
    onSubmit() {
      const usuario = this.$refs.usuarioRef.valor , senha = this.$refs.senhaRef.valor

      const userPermitidos = ['admin@dbccompany.com.br', 'henrique.laporta@dbccompany.com.br']

      const podeLogar = userPermitidos.some(element => usuario === element) && senha == 'admin'
      if ( podeLogar ) {
        this.bag.remove('auth')
        this.textoBotao = 'Carregando...'
        setTimeout( () => {
          this.$router.push( { name: 'home', params: { usuario } } )
        }, 1000)
      }
      else{
        this.bag.add({
          field: 'auth',
          msg: 'Usuario ou senha invalidos!!'
        })
      }

    },
    noop(){}
  }
}
</script>
