function popupModal() {
  var popup = document.getElementById('popup');
  if(!popup) return;

  var bg = document.getElementById('bg');
  var closeBtn = document.getElementById('closeBtn');
  var showBtn = document.getElementById('showBtn');

  function closePopup(elem) {
    if(!elem) return;
    elem.addEventListener('click', function() {
      popup.classList.toggle('is-show');
    });
  }

  closePopup(bg);
  closePopup(closeBtn);
  closePopup(showBtn);

}

popupModal();
//変数が他のコードに影響しないように全体を関数に
