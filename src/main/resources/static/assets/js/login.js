const { createApp } = Vue

const app = createApp({
    data() {
        return {
            email: '',
            password: '',
            firstName: '',
            lastName: '',
        }
    },
    methods: {
        register() {
            axios.post('/api/newClient', {
                "firstName": this.firstName,
                "lastName": this.lastName,
                "email": this.email,
                "password": this.password
            })
                .then(() => this.login())
                .catch(error => {
                    let errorMessage = error.response.data
                    if ((this.password === undefined || this.password == '') && (this.email == undefined || this.email == '')) {
                        Swal.fire({
                            icon: 'error',
                            title: errorMessage
                        })
                    } else if (this.email == undefined || this.email == '') {
                        Swal.fire({
                            icon: 'error',
                            title: errorMessage
                        })
                    } else if (this.password === undefined || this.password == '') {
                        Swal.fire({
                            icon: 'error',
                            title: errorMessage
                        })
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: errorMessage
                        })
                    }
                })
        },
        login() {
            axios.post('/api/login', `email=${this.email}&password=${this.password}`)
                .then(() => {
                    axios.get('/api/getClients')
                        .then(() => location.href = '../../admin.html')
                        .catch(() => location.href = '../../products.html')
                })
                .catch(() => {
                    if ((this.password === undefined || this.password == '') && (this.email == undefined || this.email == '')) {
                        Swal.fire({
                            icon: 'error',
                            title: 'You must fill in all the fields'
                        })
                    } else if (this.email == undefined || this.email == '') {
                        Swal.fire({
                            icon: 'error',
                            title: 'You must enter an email'
                        })
                    } else if (this.password === undefined || this.password == '') {
                        Swal.fire({
                            icon: 'error',
                            title: 'You must enter an password'
                        })
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'There is no user with these credentials'
                        })
                    }
                })
        }
    },
})
app.mount("#app")

const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const signUpButtonSm = document.getElementById('signupbtn-sm');
const signInButtonSm = document.getElementById('signinbtn-sm');
const container = document.getElementById('container');
const signInContainer = document.getElementsByClassName('sign-in-container-sm');
const signUpContainer = document.getElementsByClassName('sign-up-container-sm');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

signUpButtonSm.addEventListener('click', (e) => {
    e.preventDefault();
    signInContainer[0].classList.add('d-none');
    signUpContainer[0].classList.remove('d-none');
    signUpContainer[0].classList.add('d-flex')
});

signInButtonSm.addEventListener('click', (e) => {
    e.preventDefault();
    signUpContainer[0].classList.add('d-none');
    signInContainer[0].classList.remove('d-none');
    signInContainer[0].classList.add('d-flex')
});