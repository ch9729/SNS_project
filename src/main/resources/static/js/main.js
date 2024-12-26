document.addEventListener('DOMContentLoaded', function () {
    const likeButtons = document.querySelectorAll('.like-button');

    likeButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.stopPropagation();

            if (button.disabled) return; // 이미 클릭된 경우 무시

            button.disabled = true; // 버튼 비활성화
            const postId = button.dataset.postId;
            const userId = button.dataset.userId;

            if (!postId || !userId) {
                console.error('postId or userId is missing!');
                button.disabled = false; // 버튼 활성화
                return;
            }

            // 현재 상태 확인
            const isLiked = button.querySelector('img').alt === 'Liked';

            // URL 및 메서드 설정
            const url = isLiked
                ? `/likes/remove?userId=${encodeURIComponent(userId)}&postId=${encodeURIComponent(postId)}`
                : `/likes/add?userId=${encodeURIComponent(userId)}&postId=${encodeURIComponent(postId)}`;
            const method = isLiked ? 'DELETE' : 'POST';

            fetch(url, { method })
                .then(response => {
                    button.disabled = false; // 요청 완료 후 버튼 활성화
                    if (!response.ok) {
                        throw new Error('Failed to update like status');
                    }

                    // 상태 변경에 따라 이미지와 텍스트 변경
                    const img = button.querySelector('img');
                    img.src = isLiked ? '/img/heart.png' : '/img/colorHeart.png';
                    img.alt = isLiked ? 'Like' : 'Liked';
                })
                .catch(error => {
                    console.error(error);
                    button.disabled = false; // 에러 발생 시 버튼 활성화
                });
        });
    });
});