describe('calcularCirculo', function () {
  const expect = chai.expect
  // antes de cada teste, chama a biblioteca "chai" para poder usar as asserções de forma mais amigável (should, expect)
  // documentação: https://www.chaijs.com/
  beforeEach(function () {
    chai.should()
  })

  describe('Area', function () {
    // cada bloco "it" é um cenário de teste, como se fosse um @Test do JUnit
    // importante: garanta que o arquivo onde o código está implementado foi incluído, em rodar-testes.html
    it('deve calcular área de raio 1', function () {
      const raio = 1, tipoCalculo = 'A'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      resultado.should.equal(Math.PI)
    })

    it('deve calcular área de raio 10', function () {
      const raio = 10, tipoCalculo = 'A'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      resultado.should.equal(314.1592653589793)
    })

    it('deve calcular área de raio 25', function () {
      const raio = 25, tipoCalculo = 'A'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      resultado.should.equal(1963.4954084936207)
    })

    it('deve calcular área de raio -5', function () {
      const raio = -5, tipoCalculo = 'A'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      resultado.should.equal(78.53981633974483)
    })

    it('deve retornar "undefined"', function () {
      const raio = 1, tipoCalculo = 'a'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      expect(resultado).to.be.undefined
    })
  })

  describe('Circunferencia', function () {

    it('deve calcular circunferencia de raio 1', function () {
      const raio = 1, tipoCalculo = 'C'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      resultado.should.equal(6.283185307179586)
    })

    it('deve calcular circunferencia de raio 10', function () {
      const raio = 10, tipoCalculo = 'C'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      resultado.should.equal(62.83185307179586)
    })

    it('deve calcular circunferencia de raio -7', function () {
      const raio = -7, tipoCalculo = 'C'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      resultado.should.equal(-43.982297150257104)
    })

    it('deve retornar "undefined"', function () {
      const raio = 1, tipoCalculo = 'c'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      expect(resultado).to.be.undefined
    })

    it('deve retornar "undefined"', function () {
      const raio = 1, tipoCalculo = 'F'
      const resultado = calcularCirculo({ raio, tipoCalculo })
      expect(resultado).to.be.undefined
    })

  })

})
