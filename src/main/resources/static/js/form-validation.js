
// Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
    'use strict';

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation');

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms).forEach((form) => {
        form.addEventListener('submit', (event) => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });
})();

const buttonSubmitUser = document.querySelector('.submit-form-user');

buttonSubmitUser.addEventListener('click', validate)

function validate(e){
    // e.preventDefault();

    const inputsPageC = document.querySelectorAll('input[required]');

    inputsPageC.forEach((item)=>{
        item.classList.add('is-invalid')
    })




}