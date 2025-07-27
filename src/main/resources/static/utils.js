// 添加请求拦截器函数
function addTokenToRequest(url, options = {}) {
    const token = localStorage.getItem('token');
    if (token) {
        if (!options.headers) {
            options.headers = {};
        }
        options.headers['Authorization'] = `Bearer ${token}`;
    }
    return fetch(url, options);
}

// 检查是否已登录
function checkLogin() {
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    if (!isLoggedIn) {
        window.location.href = 'dlzc.html';
        return;
    }
}


