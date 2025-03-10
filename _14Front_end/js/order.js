// Initialize the page
$(document).ready(function () {
    fetchCustomerData();
    fetchItemData();
    setDateTime();
    fetchOrderTable();
    generateOrderId();

    // Add event listener to open the order confirmation modal
    $('#placeOrderBtn').click(function () {
        populateOrderConfirmationModal();
        $('#orderConfirmModal').modal('show');
    });
});

function generateOrderId() {
    $.ajax({
        url: "http://localhost:8080/api/v1/order/getAll",
        method: "GET",
        success: function (response) {
            if (response && response.data && response.data.length > 0) {
                // Extract the last order ID from the response
                const lastOrder = response.data[response.data.length - 1];
                const lastId = lastOrder.orderId || "ORD-000";
                const num = parseInt(lastId.replace("ORD-", "")) + 1;
                $("#orderId").val(`ORD-${String(num).padStart(3, "0")}`);
            } else {
                // If no orders exist, start with ORD-001
                $("#orderId").val("ORD-001");
            }
        },
        error: function (error) {
            console.error("Error generating order ID:", error);
            showAlert("danger", "Failed to generate order ID!");
        }
    });
}

// Load customer data into the dropdown
function fetchCustomerData() {
    $.ajax({
        url: "http://localhost:8080/api/v1/customer/get",
        success: function (response) {
            const $customerSelect = $('#customerId');
            $customerSelect.empty();
            $customerSelect.append('<option value="">Select Customer</option>');
            for (const customer of response.data || response) {
                $customerSelect.append(`<option value="${customer.id}" data-name="${customer.name}">${customer.id}</option>`);
            }
        },
        error: function (error) {
            console.error("Error fetching customer data:", error);
            showAlert("danger", "Failed to load customer data!");
        }
    });
}

// Load item data into the dropdown
function fetchItemData() {
    $.ajax({
        url: "http://localhost:8080/api/v1/item/get",
        success: function (response) {
            const $itemSelect = $('#itemCode');
            $itemSelect.empty();
            $itemSelect.append('<option value="">Select Item</option>');
            for (const item of response.data || response) {
                $itemSelect.append(`<option value="${item.code}" data-name="${item.description}" data-price="${item.unitPrice}" data-qty="${item.qtyOnHand}">${item.code}</option>`);
            }
        },
        error: function (error) {
            console.error("Error fetching item data:", error);
            showAlert("danger", "Failed to load item data!");
        }
    });
}

// Show customer name when customer is selected
$('#customerId').on('change', function () {
    const selectedOption = $(this).find(':selected');
    const customerName = selectedOption.data('name') || '';
    $('#customerName').val(customerName ? `${customerName}` : '');
});

// Show item name and price when item is selected
$('#itemCode').on('change', function () {
    const selectedOption = $(this).find(':selected');
    const itemName = selectedOption.data('name') || '';
    const itemPrice = selectedOption.data('price') || '';
    const itemQty = selectedOption.data('qty') || '';
    $('#itemName').val(itemName ? `${itemName}` : '');
    $('#itemPrice').val(itemPrice);
    $('#itemQty').val(itemQty);
});

// Calculate total when order quantity changes
$('#orderQty').on('input', function () {
    const qty = parseInt($(this).val()) || 0;
    const price = parseFloat($('#itemPrice').val()) || 0;
    const total = qty * price;
    $('#totalPrice').val(total.toFixed(2));
});

// Add item to the table
$('#addItemBtn').click(function () {
    if (validateForm()) {
        const orderId = $('#orderId').val();
        const customerName = $('#customerName').val();
        const itemCode = $('#itemCode').val();
        const itemName = $('#itemName').val();
        const itemPrice = $('#itemPrice').val();
        const itemQty = $('#orderQty').val();
        const totalPrice = $('#totalPrice').val();

        const row = `<tr>
            <td>${orderId}</td>
            <td>${customerName}</td>
            <td>${itemCode}</td>
            <td>${itemQty}</td>
            <td>${itemPrice}</td>
            <td>${totalPrice}</td>
            <td>${new Date().toLocaleString()}</td>
            <td><button class="btn btn-danger btn-sm delete-btn">Delete</button></td>
        </tr>`;
        $('#orderTable').append(row);

        clearFormFields();
    }
});

// Delete item from the table
$('#orderTable').on('click', '.delete-btn', function () {
    $(this).closest('tr').remove();
});

// Validate form fields
function validateForm() {
    const orderId = $('#orderId').val();
    const customerId = $('#customerId').val();
    const itemCode = $('#itemCode').val();
    const itemQty = $('#orderQty').val();

    if (!orderId || !customerId || !itemCode || !itemQty) {
        alert('Please fill in all required fields.');
        return false;
    }
    return true;
}

// Populate order confirmation modal with selected items
function populateOrderConfirmationModal() {
    const $orderTable = $('#orderTable');
    const $orderConfirmTable = $('#orderConfirmTable tbody');
    $orderConfirmTable.empty();

    $orderTable.find('tr').each(function () {
        const $row = $(this);
        const orderId = $row.find('td:eq(0)').text();
        const customerName = $row.find('td:eq(1)').text();
        const itemName = $row.find('td:eq(2)').text();
        const itemQty = $row.find('td:eq(3)').text();
        const itemPrice = $row.find('td:eq(4)').text();
        const totalPrice = $row.find('td:eq(5)').text();
        const orderDate = $row.find('td:eq(6)').text();

        const confirmRow = `<tr>
            <td>${orderId}</td>
            <td>${customerName}</td>
            <td>${itemName}</td>
            <td>${itemQty}</td>
            <td>${itemPrice}</td>
            <td>${totalPrice}</td>
            <td>${orderDate}</td>
        </tr>`;
        $orderConfirmTable.append(confirmRow);
    });
}

// Save order
function saveOrder() {
    const orderId = $('#orderId').val();
    const dateTime = $('#orderDate').val();
    const customerId = $('#customerId').val();
    const orderDetails = [];

    $('#orderConfirmTable tbody tr').each(function () {
        const itemCode = $(this).find('td:eq(2)').text();
        const qty = $(this).find('td:eq(3)').text();
        const subTotal = $(this).find('td:eq(5)').text();

        orderDetails.push({
            orderId: orderId,
            itemCode: itemCode,
            qty: qty,
            subTotal: subTotal
        });
    });

    if (!orderId || !dateTime || !customerId || orderDetails.length === 0) {
        showAlert("error", "All fields are required!");
        return;
    }

    const orderDTO = {
        orderId: orderId,
        dateTime: dateTime,
        customerId: customerId,
        orderDetails: orderDetails
    };

    $.ajax({
        url: "http://localhost:8080/api/v1/order/save",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(orderDTO),
        success: function (response) {
            showAlert("success", "Order Placed Successfully!");
            fetchOrderTable();
            clearForm();
            $('#orderConfirmModal').modal('hide');
        },
        error: function (error) {
            console.error("Error saving order:", error);
            showAlert("error", "Failed to place order! -> " + error);
        }
    });
}

// Fetch and display orders in the table
function fetchOrderTable() {
    $.ajax({
        url: "http://localhost:8080/api/v1/order/get",
        success: function (response) {
            const $orderTable = $('#orderTable');
            $orderTable.empty();
            for (const order of response.data) {
                for (const detail of order.orderDetails) {

                    const unitPrice = detail.qty > 0 ? (detail.subTotal / detail.qty).toFixed(2) : 0;   // Calculate unit price, handle division by zero

                    $orderTable.append(`
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.customerId}</td>
                            <td>${detail.itemCode}</td>
                            <td>${detail.qty}</td>
                            <td>${unitPrice}</td>
                            <td>${detail.subTotal}</td>
                            <td>${order.dateTime}</td>
                            <td><button class="btn btn-danger btn-sm delete-btn">Delete</button></td>
                        </tr>
                    `);
                }
            }

            generateOrderId();
        },
        error: function (error) {
            console.error("Error fetching order data:", error);
            showAlert("error", "Failed to load order data!" + error);
        }
    });
}

// Show toast
function showAlert(type, message) {
    const alertClass = type === "success" ? "bg-success" : "bg-danger";
    const alertHtml = `
            <div class="alert ${alertClass} text-white alert-dismissible fade show" role="alert">
                ${message}
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        `;

    $("#alertContainer").append(alertHtml);

    setTimeout(() => {
        $(".alert").alert("close");
    }, 3000);
}

// Set the current date and time
function setDateTime() {
    let now = new Date();
    now.setMinutes(now.getMinutes() + now.getTimezoneOffset() + 660);   // Set the timezone to Sri Lanka
    $('#orderDate').val(now.toISOString().split('T')[0] + 'T' + now.toISOString().split('T')[1].slice(0, 8));
}

setInterval(setDateTime, 1000); // Update the date-time every second

// Clear the form
function clearForm() {
    $('#orderForm')[0].reset();
    setDateTime();
}

// check order qty
$('#orderQty').on('input', function () {
    const orderQty = $('#orderQty').val().trim();
    const itemQty = $('#itemQty').val();

    if (!orderQty || isNaN(orderQty)) {
        $('#orderQtyError').removeClass('d-none').text("Please enter a valid quantity.");
        $('#placeOrderBtn').attr('disabled', true);
        return;
    }

    const orderQtyNum = parseInt(orderQty);
    const itemQtyNum = parseInt(itemQty);

    if (orderQtyNum <= 0) {
        $('#orderQtyError')
            .removeClass('d-none')
            .html('<i class="hgi-stroke hgi-alert-circle align-middle"></i> Order quantity must be greater than 0.');
        $('#placeOrderBtn').attr('disabled', true);
        return;
    }

    if (orderQtyNum > itemQtyNum) {
        $('#orderQtyError')
            .removeClass('d-none')
            .html('<i class="hgi-stroke hgi-alert-circle align-middle"></i> Insufficient quantity in stock!');
        $('#placeOrderBtn').attr('disabled', true);
    } else {
        $('#orderQtyError').addClass('d-none').text("");
        $('#placeOrderBtn').attr('disabled', false);
    }
});

function clearFormFields() {
    $('#itemCode').val('');
    $('#itemName').val('');
    $('#itemPrice').val('');
    $('#orderQty').val('');
    $('#totalPrice').val('');
}