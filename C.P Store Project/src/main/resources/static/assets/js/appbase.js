class AppBase {
    static DOMAIN_SERVER = location.origin;

    static DOMAIN_API = this.DOMAIN_SERVER + "/api";

    static API_AUTH = this.DOMAIN_API + "/auth";

    static API_CUSTOMER = this.DOMAIN_API + "/customers";

    static API_PRODUCT = this.DOMAIN_API + "/products";

    static API_CART = this.DOMAIN_API + "/carts";

    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/dg2xmq9tq/image/upload";

    static BASE_SCALE_IMAGE = "c_limit,w_286,h_180,q_100";


    static SweetAlert = class {
        static showDeleteConfirmDialog() {
            return Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            })
        }

        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 2000
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

        static showError401() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'Invalid credentials!',
            })
        }

        static showError403() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'You are not authorized to perform this function!',
            })
        }
    }
}

class LocationRegion {
    constructor(provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class Customer {
    constructor(id, fullName, email, phone, locationRegion, balance, deleted) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.locationRegion = locationRegion;
        this.balance = balance;
        this.deleted = deleted;
    }
}

class Deposit {
    constructor(id, customerId, transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }
}

class Withdraw {
    constructor(id, customerId, transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }
}


class Transfer {
    constructor(sender, recipient, transferAmount,fees,feesAmount,transactionAmount) {
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }
}

class User {
    constructor(username, password, role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}



class ProductAvatar {
    constructor(avatar, fileName, fileFolder) {
        this.avatar = avatar;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
    }
}
class Product {
    constructor(id, title, price, unit, description, avatar) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.avatar = avatar;
    }
}

class Role {
    constructor(id) {
        this.id = id;
    }
}

class CartDetail {
    constructor(id , title, price , quantity, amount , product) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.product = product;
    }
}


