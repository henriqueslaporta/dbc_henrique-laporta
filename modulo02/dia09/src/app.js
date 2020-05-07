const urlApi = 'https://pokeapi.co/api/v2/pokemon'
const pokeApi = new PokeApi( urlApi )
localStorage.setItem("idGerados", JSON.stringify([]))

let app = new Vue( {
  el: '#meuPrimeiroApp',
  data: {
    idParaBuscar: '',
    pokemon: { },
    erro: false
  },
  methods: {
    async buscar() {
      if(!isNaN(this.idParaBuscar))
        this.pokemon = await pokeApi.buscar( this.idParaBuscar )
    },
    checkId() {
      this.erro = isNaN(this.idParaBuscar)
    },
    checkRandomNumber(){
      const random = randomNumberInterval(1, 802)
      if(random != null){
        this.idParaBuscar = random
        this.buscar();
    }
    }
  }
} )

function randomNumberInterval(min,max){
  var storedId = JSON.parse(localStorage.getItem("idGerados"))
  const random = Math.round( (Math.random() * max) + min)
  if(storedId[random]){
    return null
  }
  storedId[random] = random
  localStorage.setItem("idGerados", JSON.stringify(storedId));
  return random
}
