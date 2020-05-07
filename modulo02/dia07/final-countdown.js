// https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Reference/Global_Objects/Date

function rodarPrograma () {

  var time = 30;

  const h1 = document.getElementById( 'contador' )
  
  h1.innerText = time;

  const interval = setInterval(function(){
    if(time == 0){
      stopClock()
    }
    else{
      if(time == 10){
        h1.className += ' red'
      }
      h1.innerText = --time
      }
  }, 1000)

  function stopClock(){
    clearInterval(interval)
    h1.innerText = 'Tempo encerrado!'
  }

}

rodarPrograma()
