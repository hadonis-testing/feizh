function deleteWallet(ID) {
    Swal.fire({
        title: "Delete Wallet?",
        text: "Delete this wallet?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Delete!",
        cancelButtonText: "Cancel!"
    }).then((result) => {
        if (result.isConfirmed) {
            const url = "wallet?action=delete&id=" + ID;

            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.status === "success") {
                        Swal.fire({
                            title: "Delete success!",
                            text: "Wallet deleted successfully!",
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

function addNewWallet() {
    let name = document.getElementById("name").value;
    let type = document.getElementById("type").value;
    let balance = document.getElementById("balance").value;

    const url = "wallet?action=add&name=" + name + "&type=" + type + "&balance=" + balance;

    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            if (data.status === "success") {
                Swal.fire({
                    title: "Add success!",
                    text: "Wallet added successfully!",
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

function openModal() {
    let modal = document.getElementById('add-new-wallet-modal');

    modal.classList.add('is-active', 'animate__bounceIn');
}

function closeModal() {
    let modal = document.getElementById('add-new-wallet-modal');

    modal.classList.remove('is-active');
}