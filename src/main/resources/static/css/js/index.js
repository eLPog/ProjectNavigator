document.addEventListener("DOMContentLoaded", ()=>{



})
document.getElementById('deleteProject').addEventListener('click', function () {
    const projectId = this.getAttribute('data-project-id');
    {
        Swal.fire({
            title: 'Confirm delete project',
            text: 'Delete project ?',
            icon: 'success',
            showConfirmButton:true,
            confirmButtonText:"Usun",
            showCancelButton:true,
            allowOutsideClick:false
        }).then(result => {
            if(result.isConfirmed){
                window.location.href =`/project/delete/${projectId}`
            }
        });
    }
});


