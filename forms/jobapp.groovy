form {

    question("name") {
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
    question("contact") {
        "Contact details" group: "contact", {
            "Home address" text: 200, map: 'homeAddress'
            "Postcode" number: 4, hint: "e.g. 2913", map: 'postcode'
            "Home Phone" phone: 15, map: 'homePhone'
            "Mobile Phone" phone: 15, map: 'mobilePhone'
            "Work Phone" phone: 15, map: 'workPhone'
        }
    }
    question("education") {
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
    question("role") {
        "Enter the details of the role you are applying for" group: "role", {
            "Job Reference" text: 80, required: true, map: 'jobNumber'
            "Position" text: 80, required: true, map: 'position'
            "Company" text: 80, required: true, map: 'company'
        }
    }
    //include copy of resume
    question("resume") {
        "Resume" group: "resume", {
            "Attach a copy of your resume" attachment: "resume_file", map: 'resume'
        }
    }
    //referee details
    question("referees") {
        "Enter details for two referees" group: "references", {
            "Referee" listOf: "referee", {
                "Given Names" text: 80, required: true, map: 'givenNames'
                "Last or Family Name" text: 50, required: true, map: 'lastName'
                "Contact Phone" phone: 15, required: true, map: 'phone'
            }

        }
    }
}