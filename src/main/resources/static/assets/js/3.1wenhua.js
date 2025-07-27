document.addEventListener('DOMContentLoaded', () => {
    const categoryButtons = document.querySelectorAll('.category-button');
    const productCards = document.querySelectorAll('.product-card');

    categoryButtons.forEach(button => {
        button.addEventListener('click', () => {
            // 1. 移除所有按钮的 active 类（高亮当前选中按钮）
            categoryButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');

            // 2. 获取当前点击的按钮对应的类别
            const selectedCategory = button.dataset.category;

            // 3. 如果点击的是“总述”按钮，显示所有商品卡片
            if (selectedCategory === undefined) {
                productCards.forEach(card => {
                    card.style.display = 'block'; // 显示所有商品卡片
                });
            } else {
                // 4. 遍历所有商品卡片，筛选出当前类别的商品
                productCards.forEach(card => {
                    if (card.dataset.category === selectedCategory) {
                        card.style.display = 'block'; // 仅显示当前类别
                    } else {
                        card.style.display = 'none'; // 隐藏其他类别
                    }
                });
            }
        });
    });
});
