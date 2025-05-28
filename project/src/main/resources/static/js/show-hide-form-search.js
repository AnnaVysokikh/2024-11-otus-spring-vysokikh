const btn = document.getElementById('but-search');

btn.addEventListener('click', () => {
  const form = document.getElementById('form-search');

  if (form.style.display === 'block') {
    form.style.display = 'none';
    btn.classList.remove("chng");
  } else {
    form.style.display = 'block';
    btn.classList.toggle("chng");
  }
});