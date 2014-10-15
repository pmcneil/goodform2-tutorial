import com.nerderg.goodForm.FormDefinition

class BootStrap {

    def formDataService

    def init = { servletContext ->

        String jobApplicationDefinition = """
form {

    question("Name") {
        "What is your name?" group: "names", {
            "Title" text: 10, hint: "e.g. Mr, Mrs, Ms, Miss, Dr", suggest: "title", map: "Title"
            "Given Names" text: 50, required: true, map: "givenNames"
            "Last or Family Name" text: 50, required: true, map: "lastName"
            "Have you been or are you known by any other names?" hint: "e.g. maiden name, previous married name, alias, name at birth", map: "hasAlias", {
                "List your other names" listOf: "aliases", {
                    "Other name" text: 50, map: 'alias'
                    "Type of name" text: 40, hint: "e.g maiden name", suggest: "nameType", map: 'aliasType'
                }
            }
        }
    }
    question("Contact") {
        "Contact details" group: "contact", {
            "Home address" text: 200, map: 'homeAddress'
            "Postcode" number: 4, hint: "e.g. 2913", map: 'postcode'
            "Home Phone" phone: 15, map: 'homePhone'
            "Mobile Phone" phone: 15, map: 'mobilePhone'
            "Work Phone" phone: 15, map: 'workPhone'
        }
    }
    question("Education") {
        "Education" group: "education", {
            "University" listOf: "universities", {
                "Name" text: 80, map: 'name'
                "Number of Years attended" number: 0..10, units: 'years', map: 'years'
                "Date completed" date: 'd/M/yyyy', map: 'dateComplete'
                "Degree" text: 80, map: 'degree'
                "Course" text: 80, map: 'course'
            }
            "High School" listOf: "highSchools", {
                "Name" text: 80, map: 'name'
                "Date completed" date: 'd/M/yyyy', map: 'dateComplete'
            }
        }
    }

    //role applying for
    question("Role") {
        "Enter the details of the role you are applying for" group: "role", {
            "Job Reference" text: 80, required: true, map: 'jobNumber'
            "Position" text: 80, required: true, map: 'position'
            "Company" text: 80, required: true, map: 'company'
        }
    }
    //include copy of resume
    question("Resume") {
        "Resume" group: "resume", {
            "Attach a copy of your resume" attachment: "resume_file", map: 'resume'
        }
    }
    //referee details
    question("Referees") {
        "Enter details for two referees" group: "references", {
            "Referee" listOf: "referee", {
                "Given Names" text: 80, required: true, map: 'givenNames'
                "Last or Family Name" text: 50, required: true, map: 'lastName'
                "Contact Phone" phone: 15, required: true, map: 'phone'
            }

        }
    }
}
"""
        if (!FormDefinition.findByName('JobApplication')) {
            formDataService.createNewFormVersion('JobApplication', jobApplicationDefinition)
        }

        String thelot = """
form {
    question("test") {
        "All elements" group: "theLot",
                hint: 'This is a group', {

            "Text elements" heading: 1
            "Text input 20?" text: 20,
                    map: 'text20',
                    hint: 'this is a 20 char text',
                    preamble: 'This is 20 char text with a pattern match so only letters can be entered',
                    pattern: [/[A-Za-z ]+/, 'You can only have letters'],
                    sugest: 'colour',
                    required: true,
                    default: 'hello'
            "Text input 120?" text: 120,
                    map: 'text120',
                    required: true,
                    default: 'hello is it me you are looking for?'

            "Number elements" heading: 2

            "Number by a Range?" number: 0..100,
                    map: 'numberRange',
                    hint: 'this is a number range',
                    preamble: 'This number is limited to the range of 0 to 100. decimal steps (0.1) are allowed',
                    units: '%',
                    required: true,
                    default: '12',
                    step: 0.1
            "Number by length?" number: 5,
                    map: 'numberLength',
                    hint: 'this is a 5 digit number',
                    preamble: "This number is limited to 200 with a minimum -20. However you'll get a validation error if you try 26.26 in html5 browsers",
                    required: true,
                    default: '12',
                    max: 200,
                    min: -20
            "Phone by length?" phone: 15,
                    map: 'phone1',
                    hint: 'this is a 15 digit phone number',
                    preamble: 'This phone number allows spaces and + signs',
                    required: true,
                    default: '+61419 555 666',
                    pattern: [/[0-9\\-\\+ ]{8,15}/, 'You must have at least 8 numbers, spaces + or -']
            "Money by length?" money: 6,
                    map: 'money1',
                    hint: 'this is a 6 digit money',
                    preamble: 'This input is limited to \$5000 with a minimum of \$1.5',
                    required: true,
                    default: '120.34',
                    max: 5000,
                    min: 1.5
            "Date?" date: 'dd/MM/yyyy',
                    map: 'date1',
                    hint: 'this is a dd/MM/yyyy date',
                    preamble: 'This is a date limited to a maximum of todays date and minimum of 1/1/1900',
                    required: true,
                    default: '23/3/1990',
                    max: 'today',
                    min: '1/1/1900'
            "Date and time?" datetime: 'dd/MM/yyyy',
                    map: 'datetime1',
                    hint: 'this is a dd/MM/yyyy date with time input. You can use your mouse wheel to change the time, just click and wheel.',
                    preamble: 'This is a date limited to a minimum of todays date and maximum of 1/1/2014',
                    required: true,
                    default: [date: '23/3/1990', time: '10:00am'],
                    max: '1/1/2014',
                    min: 'today'
            "Attachment?" attachment: 'mydoc',
                    map: 'attachment1',
                    hint: 'this is an attachment',
                    preamble: 'An attachment lets you ask for documents to be attached and uploaded.',
                    required: true
            "Pick one?" pick: '1',
                    hint: 'this is a pick 1 group',
                    preamble: 'Pick 1 lets you have a list of radio buttons. The radio button can expand when ticked to show more fields',
                    map: 'pick1', {
                "Red"()
                "Green"()
                "Blue" {
                    "what shade?" text: 20, map: 'shade'
                }
            }

            "Pick any?" pick: 'any',
                    hint: 'this is a pick any group',
                    preamble: 'Pick any lets you have a list of checkboxes. The checkboxes can expand when ticked to show more fields',
                    map: 'pickany', {
                "Red" map: 'red'
                "Green" map: 'green'
                "Blue" map: 'blue', {
                    "what shade?" text: 20, map: 'shade'
                }
            }

            "Yes?" map: 'yup', hint: 'A simple boolean', preamble: 'This is a simple check box to indicate yes to a question.'

            "Can I have more?" map: 'pleaseSir', {
                "Soup" map: 'soup'
                "Bread" map: 'bread'
                "Other" text: 20, map: 'other'
            }

            "Dynamically repeated questions?" each: 'lolly',
                    hint: 'each repeats the question for each thing in a list',
                    preamble: 'When you have a list of things you can dynamically add questions about those things with each.', {
                "rate {lolly} out of ten" number: 0..10, map: 'rating'
            }

            "Let me count the ways" listOf: 'ways',
                    hint: 'listOf lets the user add form sections',
                    preamble: 'When you want people to give you a variable number of answers, for example for all the people in a house hold use a listOf', {
                "I love your..." text: 30, map: 'loves'
                "How much?" number: 0..100, map: 'percentage', units: '%'
            }

            "Select something" select: ['one','two','three','four','five'],
                    map: 'selectme',
                    hint: 'select one of the options',
                    default: 'three',
                    preamble: 'Yep you can explain this with a preamble'
        }
    }
}
"""
        if (!FormDefinition.findByName('thelot')) {
            formDataService.createNewFormVersion('thelot', thelot)
        }

    }
    def destroy = {
    }
}
