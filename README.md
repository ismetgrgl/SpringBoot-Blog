
    Listeleme ve filtreleme için istenen listelemeleri yerine getirebilmesi için;
    1.Yazıları yayın tarihine göre sıralama seçeneği (published_at parametresi ile)
    • PostController üzerinde findByOrderByPublishedAtDesc metodonu kullandım.

    2.Yazıları belirli bir kategoriye göre filtreleme seçeneği (category_id parametresi ile)
    • PostController üzerinde findByCategoryInPosts metodunu kullandım.

    3.Kullanıcının yazılarını listeleme seçeneği (user_id parametresi ile).
    • PostController üzerinde getUsersPosts metodunu kullandım.

    4.Kategorileri isme göre arama seçeneği (name parametresi ile)
    • CategoryController üzerinde findByNameIgnoreCase metodunu kullandım.
    
### Beğenme için;
    beğenme işlemi:
    bir userId ve postId verilerek verilen userin verilen postu beğenmesini sağladım.

    beğeniyi geri çekme işlemi:
    likeId alınacak şekilde yaptım ve verilen Id'den beğenme işlelmini bulup o işlemi sidirdim.

    User tarafından beğenileri görme:
    UserController'da userId verilerek kullanıcının beğendiği postları getirdim.

    Post tarafından beğenileri görme:
    PostController'da postId verilerek o postu beğenen user'ları getirdim.

### Yorum için;
    yorum atma işlemi:
    bir userId postId ve posta atılacak yorum için yorum alınarak oluşturdum.
    
    yorum silme işlemi:
    yorumId istenicek ve verilen Id'den yorum işlemini bulup o işlemi sildirdim.

    yorum güncelleme işlemi:
    burda sadece yorum içerisine yazılan yorumu değiştirmek için yorumId alıncak 
    alınan yorumId'den bulunan yorumun içeriğini değiştirmesi için yeni yorum alınıcak

    User tarafından yorumları görme:
    UserController'da userId verilerek kullanıcının attığı yorumları getirdim.

    Post tarafından yorumları görme:
    PostController'da postId verilerek o posta gelen yorumları getirdim