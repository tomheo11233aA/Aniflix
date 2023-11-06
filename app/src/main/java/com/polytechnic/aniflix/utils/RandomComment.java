package com.polytechnic.aniflix.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomComment {
    static String[] array = new String[]{
            "10 điểm cho phim này",
            "Các mối quan hệ giữa các nhân vật rất thú vị và phát triển tốt theo từng tập phim.",
            "Cốt truyện của anime này thực sự rất sâu sắc và chạm đến nhiều chủ đề nhân văn.",
            "Cốt truyện hơi đơn giản và dễ đoán, nhưng tôi vẫn thích xem vì những màn đấu võ thú vị",
            "Hình ảnh và hiệu ứng thị giác trong phim thật tuyệt vời, rất đỉnh cao trong lĩnh vực hoạt họa.",
            "Nhân vật phản diện trong phim thật đáng ghét và độc ác, khiến tôi muốn đấm vào màn hình luôn!",
            "Nhìn chung đây là một anime rất vui nhộn và lôi cuốn, tôi đã xem hết cả serie một mạch!",
            "Phim hay thế",
            "Phim này thực sự rất cảm động, đã khiến tôi khóc nhiều lần rồi.",
            "Phần hoạt họa thực sự tuyệt vời! Các cảnh chiến đấu rất mượt mà và hấp dẫn",
            "Thật tuyệt khi thấy nhiều yếu tố văn hóa Nhật Bản trong anime này.",
            "Tôi không thích cái kết, nó có vẻ hơi vội vàng và để lại nhiều câu hỏi.",
            "Tôi không thích nhân vật nữ chính lắm, cô ấy có vẻ yếu đuối và luôn cần được cứu.",
            "Tôi không thích phong cách hài hước trong phim, nó hơi ngớ ngẩn và trẻ con quá.",
            "Tôi thích nhân vật chính, cậu ấy thực sự có động lực mạnh mẽ để theo đuổi ước mơ của mình",
            "Âm nhạc trong anime này thật tuyệt vời, làm tăng thêm cảm xúc cho các cảnh phim.",
            "Đây là một trong những bộ anime hay nhất mà tôi từng xem, khuyên mọi người nên thử xem!",
            "Phim này thật tuyệt vời! Tôi rất thích cách đạo diễn xây dựng không khí bí ẩn xuyên suốt phim.",
            "Diễn xuất của các diễn viên chính thật xuất sắc và thuyết phục. Họ thực sự làm sống động các nhân vật.",
            "Âm nhạc và hiệu ứng hình ảnh của phim thật hoàn hảo. Chúng làm tăng thêm cảm xúc cho những cảnh phim căng thẳng.",
            "Tuy là một bộ phim hành động nhưng phim vẫn có nhiều chi tiết về các mối quan hệ và cảm xúc của các nhân vật.",
            "Kịch bản phim có nhiều chi tiết thú vị và bất ngờ. Tôi không thể đoán trước được những gì sẽ xảy ra tiếp theo.",
            "Phim có nhiều cảnh hành động gay cấn và đầy kịch tính. Tôi không thể rời mắt khỏi màn hình.",
            "Đây là một trong những bộ phim hay nhất của thể loại phiêu lưu, giả tưởng. Tôi khuyên mọi người nên xem thử.",
            "Dàn diễn viên phụ trong phim cũng rất ấn tượng. Họ làm tốt vai trò hỗ trợ cho các nhân vật chính.",
            "Phim có nội dung ý nghĩa, nhắc nhở chúng ta về giá trị của tình bạn và lòng dũng cảm.",
            "Không gian và bối cảnh trong phim thật chân thực và chi tiết. Rất dễ để người xem đắm chìm vào thế giới đó."
    };

    public static String get() {
        Random random = new Random();
        int index = random.nextInt(array.length);
        return array[index];
    }
}
