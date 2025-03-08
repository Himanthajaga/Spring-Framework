$(document).ready(function () {
    fetchAllOrders();
});

function fetchAllOrders() {
    $.ajax({
        url: "http://localhost:8080/api/v1/order/getAll",
        method: "GET",
        success: function (response) {
            const $allOrdersTable = $('#allOrdersTable');
            $allOrdersTable.empty();
            for (const order of response.data) {
                for (const detail of order.orderDetails) {
                    const unitPrice = detail.qty > 0 ? (detail.subTotal / detail.qty).toFixed(2) : 0; // Calculate unit price, handle division by zero

                    $allOrdersTable.append(`
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.customerId}</td>
                            <td>${detail.itemCode}</td>
                            <td>${detail.qty}</td>
                            <td>${unitPrice}</td>
                            <td>${detail.subTotal}</td>
                            <td>${order.dateTime}</td>
                        </tr>
                    `);
                }
            }
        },
        error: function (error) {
            console.error("Error fetching order data:", error);
            alert("Failed to load order data!");
        }
    });
}