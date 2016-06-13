$(document).ready(function() {
    $('#formAdd').formValidation({
        err: {
            container: 'tooltip'
        },
//        trigger: 'blur',
        icon: {
            valid: 'fa fa-check',
            invalid: 'fa fa-times', 
        },
        fields: {
            nome: {
                validators: {
                    stringLength: {
                        enabled: true,
                        min: 1,
                        max:50,
                        message: 'Mínimo de 1 e máximo de 50 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '^[A-Za-zÀ-ú0-9\s\@\#\$\%\&\*]{3,50}',
                        message: 'Tecnologia inválida.'
                    }
                }
            }
        }
    });
});