package com.nerderg.goodform.tutorial

import com.nerderg.goodForm.FormController

class TheLotFormController extends FormController {

    protected static final String formName = 'thelot'

    def createForm() {
        createForm(formName)
    }

    protected Map getRuleFacts() {
        ['lolly': ['sherbet', 'gum', 'chocolate', 'carrot stick']]
    }

}
