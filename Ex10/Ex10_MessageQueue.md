1. Message queue là gì?

- Message queue là một kiến trúc cung cấp giao tiếp không đồng bộ.
- là một hộp thư, cho phép các thành phần/service trong một hệ thống (hoặc nhiều hệ thống), gửi thông tin cho nhau.
- thực hiện việc lấy message theo cơ chế FIFO – First In First Out.
- Một hệ thống dùng message queue thường có:
  - Message: Thông tin được gửi đi (có thể là text, binary hoặc JSON)
  - Message Queue: Nơi chứa những message này, cho phép producer và consumer có thể trao đổi với nhau
  - Producer: Chương trình/service tạo ra thông tin, đưa thông tin vào message queue
  - Consumer: Chương trình/service nhận message từ message queue và xử lý.

2. Đặc điểm và vận dụng

- Message queue giải quyết được khá nhiều vấn đề hóc búa

  - Đảm bảo duration/recovery: Do message đã được lưu trong queue, khi 1 service đang xử lý nhưng bị crash hoặc lỗi, ta không lo bị mất dữ liệu; vì có thể lấy message từ trong queue ra và chạy lại. Trong 1 hệ thống có nhiều consumer, nếu 1, 2 consume bị crash cũng không làm sụp toàn hệ thống
  - Phân tách hệ thống: Giúp phân tách hệ thống thành nhiều service nhỏ hơn, mỗi service chỉ xử lý 1 chức năng nhất định (Ưu nhược điểm thì các bạn xem lại bài về microservice nhé)
  - Hộ trợ rate limit, batching: Trong nhiều trường hợp, năng lực xử lý hệ thống có hạn (chỉ có thể xử lý 300 đơn hàng/s). Với message queue, ta có thể dần dần lấy đơn hàng trong queue ra xử lý, không sợ thất lại. Hoặc thay vì mỗi lần gửi email mất thời gian lâu, ta có thể đợi message queue có yêu cầu gửi 200 email rồi gửi luôn 1 lượt.
  - Dễ scaling hệ thống: Vào giờ cao điểm, nhiều truy vấn, ta có thể tăng số lượng consumer lên để xử lý được nhiều messege hơn. Khi không cần ta có thể giảm lại.
    -> Nó được sử dụng nhiều cho các dự án lớn, là thành phần quan trọng không thể thiếu của kiến trúc microservice

- Hạn chế
  - Khó xử lý đồng bộ: Không phải hệ thống nào cũng cần tới message queue. Nếu như service A gọi service B, theo cơ chế đồng bộ, cần kết quả xử lý ngay, ta nên dùng Rest hoặc gRPC sẽ tốt hơn.
  - Làm hệ thống phức tạp hơn: Thêm message queue sẽ tăng tính phức tạp của hệ thống. Ta cần phải biết rõ message nào gửi vào queue nào, ai gửi ai nhận. Lúc debug ở local cũng sẽ khó khăn hơn
  - Cần đảm bảo message format: Để gửi/nhận, 2 phía producer và consumer phải thống nhất format với nhau. Nếu không cẩn thận lỡ 1 bên thay đổi sẽ làm bên kia không đọc được dữ liệu.
    -Cần Monitoring Queue: Cần có các biện phát theo dõi (monitor), để đảm bảo lượng message queue không quá nhiều, làm đầy queue. Queue tốt nhất là queue luôn rỗng, hoặc số lượng message trong queue không tăng lên (message gửi vào queue đều bị consume hết)
- Một số message queue hay được dùng hiện này bao gồm:
  - RabbitMQ
  - Kafka
  - Amazon SQS
  - MSMQ (Microsoft Message Queuing)
  - RocketMQ
  - ZeroMQ

3. RabbitMQ

- là một phần mềm nơi các queue được định nghĩa, phục vụ cho ứng dụng với mục đích vận chuyển một hoặc nhiều message.
- đặt request vào một queue giữa web service và processing service. Lúc này sẽ đảm bảo rằng 2 process sẽ hoàn toàn tách rời nhau. Ngoài ra, queue sẽ lưu trữ những request, không bị thiếu sót request nào khi số lượng của chúng trở nên vô cùng lớn.
- Luồng message trong RabbitMQ hoạt động như sau:

  - Producer gửi một message tới một exchange. Exchange phải được chỉ định type khi được tạo.
  - Exchange nhận được message và chịu trách nhiệm định tuyến message. Việc trao đổi sẽ tính đến các thuộc tính của message và loại exchange, chẳng hạn như routing key.
  - Giữa exchange và queue phải thiết lập bindings từ trước. Exchange định tuyến message vào queue tùy thuộc vào thuộc tính của message. Trong ví dụ trên, có 2 bindings từ exchange với 2 queue khác nhau.
  - Các message vẫn ở trong queue cho đến khi chúng được consumer xử lý.
  - Consumer xử lý message.

- Những khái niệm cơ bản trong RabbitMQ
  - Producer: Ứng dụng gửi message.
  - Consumer: Ứng dụng nhận message.
  - Queue: Lưu trữ messages.
  - Message: Thông tin truyền từ Producer đến Consumer qua RabbitMQ:
    - Message body chứa dữ liệu thực tế cần được gửi
    - Message property (thuộc tính của tin nhắn) bao gồm các atribute và metadata khác nhau đi kèm với message.
  - Connection: Một kết nối TCP giữa ứng dụng và RabbitMQ broker.
  - Channel: Một kết nối ảo trong một Connection. Việc publishing hoặc consuming từ một queue đều được thực hiện trên channel.
  - Exchange: Là nơi nhận message được publish từ Producer và đẩy chúng vào queue dựa vào quy tắc của từng loại Exchange. Để nhận được message, queue phải được nằm trong ít nhất 1 Exchange:
    - Direct exchange, message được định tuyến đến queue dựa trên routing key. Nếu routing key của message khớp với routing key của queue, message sẽ được chuyển đến queue đó. Ngược lại, nếu routing key của message không khớp với bất kỳ routing key của queue nào, message sẽ bị loại bỏ.
    - Default exchange là một direct exchange mặc định, được khai báo trước mà không có tên, cho phép định tuyến message trực tiếp tới queue với tên là routing key của message. Mỗi queue đều tự động liên kết với exchange mặc định bằng một routing key giống tên của queue.
    - Fanout exchange, message được định tuyến đến tất cả các queue được liên kết với nó, bất kể chúng có routing key như nào.
    - Topic exchange, message được định tuyến đến queue dựa trên topic, cho phép sử dụng regular expression pattern trong routing key như \* để đại diện cho một ký tự và # đại diện cho không hoặc nhiều ký tự.
    - Header exchange, message được định tuyến dựa trên thuộc tính header của message thay vì routing key.
  - Binding: Đảm nhận nhiệm vụ liên kết giữa Exchange và Queue.
  - Routing key: Một key mà Exchange dựa vào đó để quyết định cách để định tuyến message đến queue. Có thể hiểu nôm na, Routing key là địa chỉ dành cho message.
  - AMQP: Giao thức Advance Message Queuing Protocol, là giao thức truyền message trong RabbitMQ.
  - User: Để có thể truy cập vào RabbitMQ, chúng ta phải có username và password. Trong RabbitMQ, mỗi user được chỉ định với một quyền hạn nào đó. User có thể được phân quyền đặc biệt cho một Vhost nào đó.
  - Virtual host/Vhost: Cung cấp những cách riêng biệt để các ứng dụng dùng chung một RabbitMQ instance. Những user khác nhau có thể có các quyền khác nhau đối với vhost khác nhau. Queue và Exchange có thể được tạo, vì vậy chúng chỉ tồn tại trong một vhost.
- Ưu điểm:
  - Open source (Mã nguồn mở)
  - Lightweight (Nhẹ)
  - Reliability (Độ tin cậy)
  - Message Prioritization (Ưu tiên tin nhắn)
  - Flexibility in controlling messaging trade-offs (Tính linh hoạt trong kiểm soát cân bằng tin nhắn)
  - Plugins for higher-latency environments (Plugin dành cho môi trường có độ trễ cao)
  - Flexible Language and Protocol Support (Hỗ trợ ngôn ngữ và giao thức linh hoạt)
  - Layers of security (Nhiều lớp bảo mật)
- Nhược điểm:
  - Việc thiết lập và định cấu hình RabbitMQ có thể phức tạp, đặc biệt đối với những người mới làm quen với khái niệm message queuing.
  - Performance Overhead (Chi phí hiệu năng)
  - Nếu consumer không thể tiêu thụ kịp message dẫn tới tích lũy message trong queue. Điều này có thể gây ra tiêu tốn tài nguyên và có khả năng dẫn đến các vấn đề về hiệu suất.
  - Việc tuần tự hóa và giải tuần tự hóa các đối tượng phức tạp có thể tốn thời gian, ảnh hưởng đến hiệu suất tổng thể.
- Ứng dụng:
  - Real-time data streaming (Truyền dữ liệu thời gian thực)
  - IoT Data Processing (Xử lý dữ liệu IoT)
  - Microservices Communication (Giao tiếp trong microservices)
  - Log Aggregation and Monitoring (Tổng hợp và giám sát log)

4. Kafka

- Apache Kafka là một nền tảng phân phối sự kiện phân tán mã nguồn mở được phát triển bởi Apache Software Foundation và được viết bằng Java và Scala.
- Kafka được tạo ra để giải quyết những thách thức trong việc xử lý lượng dữ liệu khổng lồ trong thời gian thực (real-time), cho phép các ứng dụng xuất bản (publish), đăng ký (subscribe), lưu trữ (store) và xử lý (process) các luồng bản ghi (streaming event) một cách hiệu quả.
- Các thành phần chính trong Kafka:

  - Kafka event (sự kiện) ghi lại thực tế rằng "điều gì đó đã xảy ra" trên thế giới hoặc trong doanh nghiệp của bạn. Nó còn được gọi là record (bản ghi) hoặc message (thông điệp).
  - Các event được tổ chức và lưu trữ lâu dài trong các Kafka topics (chủ đề).
  - Kafka được chạy dưới dạng một Kafka cluster gồm một hoặc nhiều Kafka server có thể mở rộng trên nhiều data center hoặc cloud. Một Kafka server này tạo thành lớp lưu trữ, được gọi là Kafka broker. Brokers chịu trách nhiệm quản lý bộ lưu trữ, xử lý các yêu cầu đọc và ghi cũng như sao chép dữ liệu trên toàn cluster (cụm).
  - Các topic được chia thành các partitions (phân vùng), là đơn vị cơ bản của tính song song và phân phối trong Kafka. Kafka đảm bảo độ bền của dữ liệu bằng cách sao chép dữ liệu (replication) trên nhiều brokers.
  - Kafka Producer là một client appication (ứng dụng khách), publish (xuất bản) event vào một topic cụ thể trong Kafka và luôn ghi vào leader broker.
  - Kafka consumer là một client application (ứng dụng khách), subscribe (đăng ký) một hoặc nhiều Kafka topics và đọc các bản ghi theo thứ tự chúng được tạo ra. Consumers đọc dữ liệu theo thời gian thực hoặc theo tốc độ của riêng chúng, cho phép các ứng dụng phản ứng với các sự kiện khi chúng xảy ra.
  - ZooKeeper là một dịch vụ điều phối phân tán mã nguồn mở thuộc Apache Software Foundation, nhằm duy trì thông tin cấu hình, đặt tên, cung cấp đồng bộ hóa phân tán và cung cấp dịch vụ nhóm trong hệ thống phân tán.
  - Apache Kafka APIs bao gồm 5 loại APIs chính:
    - Kafka Producer API cho phép các ứng dụng gửi luồng dữ liệu đến các Topics trong Kafka clusters.
    - Kafka Consumer API cho phép các ứng dụng đọc luồng dữ liệu từ các Topics trong Kafka clusters.
    - Kafka Streams API cho phép chuyển đổi luồng dữ liệu từ Topic đầu vào sang Topic đầu ra.
    - Kafka Connect API cho phép triển khai các trình kết nối liên tục kéo từ một số hệ thống nguồn hoặc ứng dụng vào Kafka hoặc đẩy từ Kafka vào một số hệ thống hoặc ứng dụng background.
    - Kafka Admin API cho phép quản lý và kiểm tra các Topics, Brokers và các đối tượng Kafka khác.

- Ưu điểm:
  - Scalability (Khả năng mở rộng)
  - Durability (Độ bền)
  - Real-time processing (Xử lý theo thời gian thực)
  - Open-source (Mã nguồn mở)
  - Long polling: Mặc dù long polling không phải là một tính năng tích hợp sẵn của Kafka, nhưng khái niệm này có thể được áp dụng cho cách Kafka consumers tương tác với các Kafka brokers để đạt được mức tiêu thụ dữ liệu hiệu quả.
- Nhược điểm:
  - ZooKeepers dependency (Phụ thuộc vào Zookeepers)
  - Complexity (Cài đặt, cấu hình và quản lý phức tạp)
  - Resource requirement (Yêu cầu tài nguyên)
- Ứng dụng:
  - Activity tracking (Theo dõi hoạt động)
  - Metrics and Logging (Số liệu và ghi nhật ký)
  - Stream processing (Xử lý luồng)
  - Event Sourcing (Tìm nguồn sự kiện)
  - Data Integration (Tích hợp dữ liệu)
  - Commit Log

5. So sánh RabbitMQ và Kafka

- Kiến trúc:
  - Kiến trúc của RabbitMQ được thiết kế cho việc định tuyến thông điệp phức tạp. RabbitMQ sử dụng mô hình đẩy. Đối tượng tạo sẽ gửi các thông điệp đến đối tượng nhận kèm theo các quy tắc khác nhau.
  - Kafka sử dụng thiết kế dựa trên phân vùng để xử lý luồng thông lượng cao theo thời gian thực. Kafka sử dụng mô hình kéo. Các đối tượng tạo gửi thông điệp tới các topic và phân vùng mà đối tượng nhận đăng ký nhận.
- Xử lý thông điệp:
  - Các trình truyền tải RabbitMQ giám sát việc xử lý thông điệp. Các trình này sẽ xóa thông điệp sau khi thông điệp được xử lý. Trình truyền tải hỗ trợ các ưu tiên thông điệp.
  - Đối tượng nhận theo dõi việc truy xuất thông điệp bằng bộ theo dõi thứ tự. Kafka giữ lại các thông điệp theo chính sách lưu giữ. Không có ưu tiên thông điệp.
- Hiệu năng:
  - RabbitMQ có độ trễ thấp. RabbitMQ gửi hàng ngàn thông điệp mỗi giây.
  - Kafka có khả năng truyền tải theo thời gian thực lên đến hàng triệu thông điệp mỗi giây.
- Ngôn ngữ:
  - RabbitMQ hỗ trợ một loạt các ngôn ngữ và giao thức cũ.
  - Kafka có số lựa chọn hạn chế về ngôn ngữ lập trình. Kafka sử dụng giao thức nhị phân qua TCP để truyền dữ liệu.
