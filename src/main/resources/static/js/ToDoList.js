var btns = document.getElementsByName('deletebtn');
var alerts = document.getElementsByName('deleteAlart');
var count;
for ( var i = 0; i <btns.length; i++){
  count = i;
  btns[i].addEventListener('click',function() {
    alerts[count].style.display = 'block';
  });
}

var compbtns = document.getElementsByName('compbtn');
var completealerts = document.getElementsByName('completeAlart');
for ( var i = 0; i < compbtns.length; i++){
  subcount = i;
  compbtns[i].addEventListener('click',function() {
    completealerts[subcount].style.display = 'block';
  });
}

var cancelBtn = document.getElementsByName('cancel');
for ( var i = 0; i <btns.length; i++){
  count = i;
  cancelBtn[i].addEventListener('click',function() {
    alerts[count].style.display = 'none';
    completealerts[count].style.display = 'none';
  });
}
