$(document).ready(function () {
    var forms = document.getElementsByClassName('needs-validation');
    var validation = Array.prototype.filter.call(forms, function (form) {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });

    $('#privilegesInRole').multiSelect({
        selectableHeader: "<input type='text' class='multi-search-input' autocomplete='off' placeholder=''>",
        selectionHeader: "<input type='text' class='multi-search-input' autocomplete='off' placeholder=''>",
        afterInit: function(ms){
            var that = this,
                $selectableSearch = that.$selectableUl.prev(),
                $selectionSearch = that.$selectionUl.prev(),
                selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
                selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';

            that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                .on('keydown', function(e){
                    if (e.which === 40){
                        that.$selectableUl.focus();
                        return false;
                    }
                });

            that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                .on('keydown', function(e){
                    if (e.which == 40){
                        that.$selectionUl.focus();
                        return false;
                    }
                });
        },
        afterSelect: function(){
            this.qs1.cache();
            this.qs2.cache();
        },
        afterDeselect: function(){
            this.qs1.cache();
            this.qs2.cache();
        }
    });

    // Persian date picker
    // TODO: it doesn't work correctly
    // $('.persianDate').pDatepicker({
    //     inline: false,
    //     format: "LLL",
    //     viewMode: "day",
    //     initialValue: false,
    //     initialValueType: "gregorian",
    //     minDate: 1579971647710,
    //     maxDate: 1580922047727,
    //     autoClose: true,
    //     position: [0,0],
    //     altFormat: "lll",
    //     altField: "#altfieldExample",
    //     onlyTimePicker: false,
    //     onlySelectOnDate: false,
    //     calendarType: "persian",
    //     inputDelay: 800,
    //     observer: false,
    //     calendar: {
    //         persian: {
    //             locale: "fa",
    //             showHint: true,
    //             leapYearMode: "algorithmic"
    //         },
    //         gregorian: {
    //             locale: "en",
    //             showHint: true
    //         }
    //     },
    //     navigator: {
    //         enabled: true,
    //         scroll: {
    //             enabled: true
    //         },
    //         text: {
    //             btnNextText: "<",
    //             btnPrevText: ">"
    //         }
    //     },
    //     toolbox: {
    //         enabled: true,
    //         calendarSwitch: {
    //             enabled: true,
    //             format: "MMMM"
    //         },
    //         todayButton: {
    //             enabled: true,
    //             text: {
    //                 fa: "امروز",
    //                 en: "Today"
    //             }
    //         },
    //         submitButton: {
    //             enabled: true,
    //             text: {
    //                 fa: "تایید",
    //                 en: "Submit"
    //             }
    //         },
    //         text: {
    //             btnToday: "امروز"
    //         }
    //     },
    //     timePicker: {
    //         enabled: false,
    //         step: 1,
    //         hour: {
    //             enabled: false,
    //             step: null
    //         },
    //         minute: {
    //             enabled: false,
    //             step: null
    //         },
    //         second: {
    //             enabled: false,
    //             step: null
    //         },
    //         meridian: {
    //             enabled: false
    //         }
    //     },
    //     dayPicker: {
    //         enabled: true,
    //         titleFormat: "YYYY MMMM"
    //     },
    //     monthPicker: {
    //         enabled: true,
    //         titleFormat: "YYYY"
    //     },
    //     yearPicker: {
    //         enabled: true,
    //         titleFormat: "YYYY"
    //     },
    //     responsive: true
    // });
});


// $(".needs-validation").validate({
//     rules: {
//         firstName: "required",
//         lastName: "required",
//         nationalCode: {
//             required: true,
//             minlength: 10,
//             maxlength: 10
//         },
//         password1: {
//             required: true,
//             minlength: 5
//         },
//         confirm_password1: {
//             required: true,
//             minlength: 5,
//             equalTo: "#password1"
//         },
//         email1: {
//             required: true,
//             email: true
//         },
//         agree1: "required"
//     },
//     messages: {
//         firstname1: "Please enter your firstname",
//         lastname1: "Please enter your lastname",
//         username1: {
//             required: "Please enter a username",
//             minlength: "Your username must consist of at least 2 characters"
//         },
//         password1: {
//             required: "Please provide a password",
//             minlength: "Your password must be at least 5 characters long"
//         },
//         confirm_password1: {
//             required: "Please provide a password",
//             minlength: "Your password must be at least 5 characters long",
//             equalTo: "Please enter the same password as above"
//         },
//         email1: "Please enter a valid email address",
//         agree1: "Please accept our policy"
//     },
//     errorElement: "em",
//     errorPlacement: function (error, element) {
//         // Add the `help-block` class to the error element
//         error.addClass("help-block");
//
//         // Add `has-feedback` class to the parent div.form-group
//         // in order to add icons to inputs
//         element.parents(".col-sm-5").addClass("has-feedback");
//
//         if (element.prop("type") === "checkbox") {
//             error.insertAfter(element.parent("label"));
//         } else {
//             error.insertAfter(element);
//         }
//
//         // Add the span element, if doesn't exists, and apply the icon classes to it.
//         if (!element.next("span")[0]) {
//             $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
//         }
//     },
//     success: function (label, element) {
//         // Add the span element, if doesn't exists, and apply the icon classes to it.
//         if (!$(element).next("span")[0]) {
//             $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
//         }
//     },
//     highlight: function (element, errorClass, validClass) {
//         $(element).parents(".col-sm-5").addClass("has-error").removeClass("has-success");
//         $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
//     },
//     unhighlight: function (element, errorClass, validClass) {
//         $(element).parents(".col-sm-5").addClass("has-success").removeClass("has-error");
//         $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
//     }
// });
// $(document).ready(function () {
//     $("#birthDate").pDatepicker(
//         {
//             altField: '#birthDate',
//             altFormat: "YYYY/MM/DD",
//             observer: true,
//             format: 'YYYY/MM/DD',
//             initialValue: false,
//             initialValueType: 'persian',
//             autoClose: true,
//             maxDate: 'today',
//         }
//     );
// });
