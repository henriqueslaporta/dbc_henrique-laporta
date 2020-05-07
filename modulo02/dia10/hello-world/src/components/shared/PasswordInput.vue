<template>
  <div class="form-group row">
    <label v-bind:for="nome">Senha: </label>
    <input type="password" 
            v-bind:name="nome" 
            v-validate="'required|min:5'" 
            data-vv-delay="1000"
            v-model="valor" 
            v-bind:class="{'border': errors.has( this.nome ), 'border-danger': errors.has( this.nome )}" 
            class="form-control" 
            v-bind:id="nome" 
            placeholder="senha"
    >
    <div v-show="errors.has( this.nome )" class="text-danger" role="alert">
      <span>{{ errors.first( this.nome ) }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PasswordInput',
  props: {
    nome: String
  },
  data: () => {
    return {
      valor: ''
    }
  }
}
</script>
