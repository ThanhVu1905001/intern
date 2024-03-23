1. RestAPI là gì?

- là một giao diện lập trình ứng dụng (API) mà tuân thủ các ràng buộc và quy ước kiến trúc REST được sử dụng trong việc giao tiếp giữa client và server. REST là viết tắt của REpresentational State Transfer.
- REST API thường sử dụng giao thức HTTP/1 kèm theo các định nghĩa trước đó mà cả client và server cần tuân thủ.

2. Hai thành phần chính trong RestAPI là Rest và API

- API là giao diện lập trình ứng dụng, thể hiện các khai báo (tên, thao số, kiểu trả về)
- Rest là đại diện cho sự chuyển đổi dữ liệu. Giữa 2 bên (client và sever) trao đổi state, chúng sẽ buộc phải thông qua các resource.

3. Method(phương thức)

- Để trao đổi state chúng ta cần thông qua việc gửi các request response thông qua HTTP/1 để giao tiếp resource, cần chỉ định các phương thức tương ứng gồm:
  . GET: Trả về một Resource hoặc một danh sách Resource.
  . POST: Tạo mới một Resource.
  . PUT: Cập nhật thông tin cho Resource (toàn bộ resource).
  . PATCH: Cật nhật thông tin cho resourse (một phần resource).
  . DELETE: Xoá một Resource.

4. Header: Authentication và quy định kiểu dữ liệu trả về

- Nếu một request cần xác thực quyền truy cập, chúng sẽ phải dùng thêm thông tin trong header.
- Header còn giúp client chỉ định được loại content cần trả về từ server – content type. Việc này được thực hiện thông qua phần Accept trong header. Giá trị của nó thường là MIME type:

. image — image/png, image/jpeg, image/gif
. audio — audio/wav, audio/mpeg
. video — video/mp4, video/ogg
. application — application/json, application/pdf, application/xml, application/octet-stream

5. Ràng buộc và quy ước

- Thiết kế REST API:
  . Sử dụng các method request để nói lên được nhiệm vụ của API.
  . Phần URI có thể giống nhau.
  . Không cần cứ phải chứa các động từ như: create, get, update, delete.
  . Resource name sẽ ở dạng số nhiều.
  . Sử dụng đúng Status Code. Nếu API trả về lỗi, các bạn hãy dùng đúng status nhé, tránh luôn trả về status 2xx khi mà trong body là error message (cái này nhiều bạn đang làm sai lắm).
  . Đừng dùng underscore, hãy dùng hyphen (-) trong URI
  . Trong URI đều là chữ viết thường (lowercase)
  . Đừng nên sử dụng đuôi file (extension) trong URI (VD: .html, .xml, .json).
