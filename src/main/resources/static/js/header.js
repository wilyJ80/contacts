const menuBtn = document.querySelector('#menu-btn');
const navbarSticky = document.querySelector('#navbar-sticky');

menuBtn.addEventListener('click', () => {
	navbarSticky.classList.toggle('hidden');
});
