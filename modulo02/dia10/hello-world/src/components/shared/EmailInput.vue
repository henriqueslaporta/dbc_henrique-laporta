<template>
  <div class="form-group row">
    <label v-bind:for="nome">Email: </label>
    <input type="text" 
          v-bind:name="nome" 
          v-validate="'required|email|email-dbc'" 
          data-vv-delay="1000"
          v-model="valor" 
          v-bind:class="{'border': errors.has( this.nome ), 'border-danger': errors.has( this.nome )}" 
          class="form-control" 
          v-bind:id="nome" 
          placeholder="usuario@dbccompany.com.br"
    >
    <div v-show="errors.has( this.nome )" class="text-danger" role="alert">
      <span>{{ errors.first( this.nome ) }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EmailInput',
  props: {
    nome: String
  },
  data: () => {
    return {
      valor: ''
    }
  },
  created() {
    this.$validator.extend('email-dbc', {
      getMessage: () => 'O email deve pertencer ao dominio @dbccompany.com.br',
      validate: (value) => {
        const split = value.split('@')
        const dominio = 'dbccompany.com.br'
        return split[1] === dominio;
      }
    })
  }
}
</script>
