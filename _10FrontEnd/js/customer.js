function saveCustomer() {
    const customer = {
        id: $('#customerId').val(),
        name: $('#customerName').val(),
        address: $('#customerAddress').val(),
        age: $('#customerAge').val(),
        image: $('#customerImage')[0].files[0] ? URL.createObjectURL($('#customerImage')[0].files[0]) : ''
    };

    $.ajax({
        url: 'http://localhost:8080/_11ApiBackend_Web_exploded/api/v1/customer/save',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(customer),
        success: function(response) {
            alert('Customer saved successfully');
            clearFields();
            refreshTable();
        },
        error: function(error) {
            console.error('Error saving customer:', error);
        }
    });
}

function updateCustomer() {
    const customer = {
        id: $('#customerId').val(),
        name: $('#customerName').val(),
        address: $('#customerAddress').val(),
        age: $('#customerAge').val(),
        image: $('#customerImage')[0].files[0] ? URL.createObjectURL($('#customerImage')[0].files[0]) : ''
    };

    $.ajax({
        url: 'http://localhost:8080/_11ApiBackend_Web_exploded/api/v1/customer/update',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(customer),
        success: function(response) {
            alert('Customer updated successfully');
            updateTableRow(customer);
            clearFields();
        },
        error: function(error) {
            console.error('Error updating customer:', error);
        }
    });
}

function updateTableRow(customer) {
    const row = $(`#customerTableBody tr:has(td:contains('${customer.id}'))`);
    row.find('td:eq(1)').text(customer.name);
    row.find('td:eq(2)').text(customer.address);
    row.find('td:eq(3)').text(customer.age);
    row.find('td:eq(4) img').attr('src', customer.image);
}

function deleteCustomer() {
    const customerId = $('#customerId').val();

    $.ajax({
        url: `http://localhost:8080/_11ApiBackend_Web_exploded/api/v1/customer/delete/${customerId}`,
        type: 'DELETE',
        success: function(response) {
            alert('Customer deleted successfully');
            clearFields();
            refreshTable();
        },
        error: function(error) {
            console.error('Error deleting customer:', error);
        }
    });
}

function refreshTable() {
    $.ajax({
        url: 'http://localhost:8080/_11ApiBackend_Web_exploded/api/v1/customer/getAll',
        type: 'GET',
        success: function(customers) {
            const tableBody = $('#customerTableBody');
            tableBody.empty();
            customers.forEach(customer => {
                const row = `<tr onclick="loadCustomer('${customer.id}', '${customer.name}', '${customer.address}', ${customer.age}, '${customer.image}')">
          <td>${customer.id}</td>
          <td>${customer.name}</td>
          <td>${customer.address}</td>
          <td>${customer.age}</td>
          <td><img src="${customer.image}" alt="Customer Image" width="50" height="50"></td>
        </tr>`;
                tableBody.append(row);
            });
        },
        error: function(error) {
            console.error('Error fetching customers:', error);
        }
    });
}

function clearFields() {
    $('#customerId').val('');
    $('#customerName').val('');
    $('#customerAddress').val('');
    $('#customerAge').val('');
    $('#customerImage').val('');
}

function loadCustomer(id, name, address, age, image) {
    $('#customerId').val(id);
    $('#customerName').val(name);
    $('#customerAddress').val(address);
    $('#customerAge').val(age);
    $('#customerImage').val('');
}

$(document).ready(function() {
    refreshTable();
});