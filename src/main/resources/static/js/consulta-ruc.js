// Call the dataTables jQuery plugin

  "use strict";

  const header = {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  };
  let legal_info = {};
  let table;

  $(document).ready(function () {
    $("#register-legal-person").hide();
    $("#btnSearch").on("click", () => {
      const ruc = $("#txtSearch").val();
      if (!ruc) {
        alert("ingrese ruc");
      } else {
        getRuc(ruc);
      }
    });
    $("#register-legal-person").on("click", () => {
      if (legal_info) {
        register(legal_info);
      }
    });
    loadTable();
  });

  async function getRuc(ruc) {
    const request = await fetch(`api/ruc/${ruc}`, {
      method: "GET",
      headers: header
    });
    const result = await request.json();
    if(result.success){
      if(!result.data.ruc){
        $("#info-legal-person").html(`<div class="text-s font-weight-bold text-info text-uppercase mb-1 text-center">RUC NO REGISTRADO</div>`)
        $("#register-legal-person").hide();
      }else{
        legal_info = result.data;
        showInfo(result.data);
        $("#register-legal-person").show();
      }
    }
  }
  async function register(data){
    const request = await fetch("api/ruc/register", {
      method: "POST",
      headers: header,
      body: JSON.stringify(data)
    });
    const response = await request.json();
    if(response.success){
      Swal.fire(
          'Éxito!',
          response.message,
          'success'
      );
      refreshData();
    }else{
      Swal.fire(
          'Alerta!',
          response.message,
          'warning'
      );
    }
  }
  async function deleteRuc(target){
    Swal.fire({
      title: 'Esta seguro de eliminar?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si',
      cancelButtonText: 'Cancelar',
    }).then(async (result) => {
      if (result.isConfirmed) {
        const url = target.getAttribute("data-link");
        const request = await fetch(url, {
          method: "DELETE",
          headers: header
        });
        const response = await request.json();
        if(response.success){
          Swal.fire(
              'Éxito!',
              response.message,
              'success'
          );
          refreshData();
        }else{
          Swal.fire(
              'Error!',
              response.message,
              'error'
          );
        }
      }
    })
  }

  function showInfo(data){
  const html =  `<p><strong className="mr-1">Ruc: </strong>${data.ruc}</p>
    <p><strong className="mr-1">Razon Social: </strong>${data.razon_social}</p>
    <p><strong className="mr-1">Dirección: </strong>${data.direccion}</p>
    <p><strong className="mr-1">Ubigeo: </strong>${data.ubigeo}</p>
    <p><strong className="mr-1">Departamento: </strong>${data.departamento}</p>
    <p><strong className="mr-1">Provincia: </strong>${data.provincia}</p>
    <p><strong className="mr-1">Distrito: </strong>${data.distrito}</p>
    <p><strong className="mr-1">Estado: </strong>${data.estado}</p>`
  $("#info-legal-person").html(html)
}
  function loadTable(){
   $(`#dataTable`).DataTable({
       language: {
         "lengthMenu": "Mostrar _MENU_ registros por página",
         "zeroRecords": "No hay registros disponibles",
         "info": "Mostrando página _PAGE_ de _PAGES_",
         "infoEmpty": "No hay registros disponibles",
         "search": "Buscar"
       },
        columns: [
          { data: 'ruc', title: "Ruc" },
          { data: 'razon_social', title: "Razon Social"},
          { data: 'direccion', title: "Dirección"},
          { data: 'ubigeo', title: "Ubigeo"},
          { data: 'departamento',  title: "Departamento" },
          { data: 'provincia',  title: "Provincia" },
          { data: 'distrito',  title: "Distrito" },
          { data: 'estado',  title: "Estado" },
          { data: 'ruc',  title: "Acciones", render: function (value) {
              return `<a  data-link="api/ruc/delete/${value}" onclick="deleteRuc(this)" href="#" class="btn btn-sm btn-danger"><i class="fa fa-times-circle"></i></a>`
            }}
        ],
        ajax: function (d, callback) {
          $.ajax({
            url: 'api/ruc/getAll',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            async: true,
            success: function (response) {
              callback({
                data: response.data,
                recordsTotal: response.data.length,
                recordsFiltered: response.data.length,
              });

            },
            error: function (error) {
              console.log("Error", error)
            }});
        },
      });
  }
  function refreshData(){
    $('#dataTable').DataTable().ajax.reload();
  }


