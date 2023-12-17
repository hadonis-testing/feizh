function deleteCategory(ID) {
    Swal.fire({
        title: "Delete Category?",
        text: "Delete this Category?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Delete!",
        cancelButtonText: "Cancel!"
    }).then((result) => {
        if (result.isConfirmed) {
            const url = "category?action=delete&id=" + ID;

            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.status === "success") {
                        Swal.fire({
                            title: "Delete success!",
                            text: "Category deleted successfully!",
                            icon: "success"
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.reload();
                            }
                        });
                    } else {
                        Swal.fire({
                            title: "Oops...",
                            text: "An error has occurred!",
                            icon: "error"
                        });
                    }
                },
                error: function () {
                    Swal.fire({
                        title: "Oops...",
                        text: "An error occurred while sending the request!",
                        icon: "error"
                    });
                }
            });
        }
    });
}

function openModal() {
    let modal = document.getElementById('add-new-category-modal');

    modal.classList.add('is-active', 'animate__bounceIn');
}

function closeModal() {
    let modal = document.getElementById('add-new-category-modal');

    modal.classList.remove('is-active');
}