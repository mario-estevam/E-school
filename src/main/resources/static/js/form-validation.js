
const buttonSubmitUser = document.querySelector('.submit-form-user');



buttonSubmitUser.addEventListener('click', validate)

function validate(e){
    // e.preventDefault();

    const inputsPageC = document.querySelectorAll('input[required], select[required]');

    inputsPageC.forEach((item)=>{
        if(item.value === ""){
            item.classList.add('is-invalid')

        }

    })


}

$(document).ready(function(){
    $('.cpf2').mask('000.000.000-00');
    $('.rg').mask('000.000.000')
});