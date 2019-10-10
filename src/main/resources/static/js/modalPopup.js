function popupModal() {
  var popup = document.getElementById('popup');
  if(!popup) return;

  var bg = document.getElementById('bg');
  var closeBtn = document.getElementById('closeBtn');
  var showBtn = document.getElementsByClassName('showBtn');

  function closePopup(elem) {
    if(!elem) return;
    elem.addEventListener('click', function() {
      popup.classList.toggle('is-show');
    });
  }

  closePopup(bg);
  closePopup(closeBtn);
  for (var i = 0; i < showBtn.length; i++) {
   closePopup(showBtn[i])
  }
}

popupModal();
//変数が他のコードに影響しないように全体を関数に
