$('.textbox input').attr('value', "")
$('.textbox input').attr('onkeyup', "this.setAttribute('value', this.value);")
$('.textbox .info').append('<i class="material-icons">info</i>')