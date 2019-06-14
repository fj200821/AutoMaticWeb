/*
 *  Document   : readyRegister.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Register page
 */

var ReadyRegister = function() {

    return {
        init: function() {
            /*
             *  Jquery Validation, Check out more examples and documentation at https://github.com/jzaefferer/jquery-validation
             */

            /* Register form - Initialize Validation */
            $('#form-register').validate({
                errorClass: 'help-block animation-slideUp', // You can change the animation class for a different entrance animation - check animations page
                errorElement: 'div',
                errorPlacement: function(error, e) {
                    e.parents('.form-group > div').append(error);
                },
                highlight: function(e) {
                    $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
                    $(e).closest('.help-block').remove();
                },
                success: function(e) {
                    if (e.closest('.form-group').find('.help-block').length === 2) {
                        e.closest('.help-block').remove();
                    } else {
                        e.closest('.form-group').removeClass('has-success has-error');
                        e.closest('.help-block').remove();
                    }
                },
                rules: {
                    'Name': {
                        required: true,
                        minlength: 3
                    },
                    'LoginId': {
                        required: true,
                        minlength: 3
                    },
                    'Email': {
                        required: true,
                        email: true
                    },
                    'Password': {
                        required: true,
                        minlength: 5
                    },
                    'Password-verify': {
                        required: true,
                        equalTo: '#register-password'
                    },
                    'CertificateType': {
                        required: true,
                    },
                    'IDNumber': {
                        required: true,
                    },
                    'register-terms': {
                        required: true
                    }
                },
                messages: {
                    'Name': {
                        required: 'Please enter a username',
                        minlength: 'Please enter a username '
                    },
                    'LoginId': {
                        required: 'Please enter a loginName',
                        minlength: 'Please enter a loginName'
                    },
                    'Email': 'Please enter a valid email address',
                    'Password': {
                        required: 'Please provide a password',
                        minlength: 'Your password must be at least 5 characters long'
                    },
                    'Password-verify': {
                        required: 'Please provide a password',
                        minlength: 'Your password must be at least 5 characters long',
                        equalTo: 'Please enter the same password as above'
                    },
                    'CertificateType': {
                        required: 'Please accept the CertificateType!'
                    },
                    'IDNumber': {
                        required: 'Please accept the Utils!'
                    },
                    'register-terms': {
                        required: 'Please accept the terms!'
                    }
                }
            });
        }
    };
}();