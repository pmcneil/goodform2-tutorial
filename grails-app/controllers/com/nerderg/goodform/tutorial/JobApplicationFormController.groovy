package com.nerderg.goodform.tutorial

import com.nerderg.goodForm.FormController
import com.nerderg.goodForm.FormInstance

class JobApplicationFormController extends FormController {

    protected static final String formName = 'JobApplication'

    def createForm() {
        createForm(formName)
    }

    protected Map getRuleFacts() {
        // pre-fill the users name from data we know...
        [Name: [names: [givenNames: 'Peter', lastName: 'McNeil']]]
    }

    def submit(Long id) {
        FormInstance formInstance = formDataService.getFormInstance(id)
        if(!formInstance.readOnly) {
            formInstance.setReadOnly(true)
            formInstance.save(flush: true)
            Map data = formInstance.storedFormData()
            render(contentType: 'application/json') {
                data
            }
        } else {
            flash.message = "Form already submitted, can't do that again."
            redirect(action: 'view', id: id)
        }

    }
}
