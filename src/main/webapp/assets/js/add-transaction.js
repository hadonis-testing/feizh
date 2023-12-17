const form = document.querySelector('#add-transaction-form');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    Swal.fire({
        title: "Add Transaction",
        text: "Add New Transaction?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Add!",
        cancelButtonText: "Cancel!"
    }).then((result) => {
        if (result.isConfirmed) {
            form.submit();
        }
    });
});