
drop table if exists authority;
 drop table if exists comment;
 drop table if exists groups;
 drop table if exists hash_tag;
 drop table if exists likes;
 drop table if exists message;
 drop table if exists message_recipient;
 drop table if exists password_reset_token;
 drop table if exists photo;
 drop table if exists photo_comments;
 drop table if exists photo_likes;
 drop table if exists photo_tags;
 drop table if exists photos_hashtags;
 drop table if exists post;
 drop table if exists post_photo;
 drop table if exists relation;
 drop table if exists role;
 drop table if exists role_users;
 drop table if exists tags;
 drop table if exists user;
 drop table if exists user_authority;
 drop table if exists user_group;
 
 
 
 
 create table authority (
   id bigint not null auto_increment,
   name varchar(255),
   primary key (id));
 
   create table comment (
     id integer not null auto_increment,
     comment varchar(255), user_id integer,
     primary key (id));

   create table groups (
     id integer not null auto_increment,
     date_created datetime,
     is_active bit not null,
     name varchar(255),
     primary key (id)) ;

   create table hash_tag (
     id integer not null auto_increment,
     hash_tag_name varchar(255),
     primary key (id));

   create table likes (
     id integer not null auto_increment,
     date_created datetime,
     date_updated datetime,
     photo_id integer,
     user_id integer,
     primary key (id));

   create table message (
     id integer not null auto_increment,
     date_created datetime,
     expiry_date datetime,
     is_reminder integer,
     message_body varchar(255),
     next_remind_date datetime,
     remind_frequency_id integer,
     subject varchar(255),
     parent_message_id integer,
     user_id integer,
     primary key (id)) ;

   create table message_recipient (
     id integer, is_read bit not null,
     recipient_group_id integer not null,
     recipient_id integer not null,
     message_id integer, primary key (recipient_group_id, recipient_id)) ;

   create table password_reset_token (id bigint not null auto_increment,
     expiry_date datetime, token varchar(255),
     user_id integer not null, primary key (id)) ;

   create table photo (id integer not null auto_increment,
     caption varchar(255), date_created date,
     date_updated date, image_path varchar(255),
     image_size integer,
     latitude float,
     longitude float,
     user_id integer,
     primary key (id)) ;

   create table photo_comments (
     photo_id integer not null,
     comment_id integer not null,
     primary key (photo_id, comment_id)) ;

   create table photo_likes (
     photo_id integer not null,
     likes_id integer not null) ;

   create table photo_tags (
     photo_id integer not null,
     tags_id integer not null,
     primary key (photo_id, tags_id)) ;

   create table photos_hashtags (
     photo_id integer not null,
     hashtag_id integer not null,
     primary key (photo_id, hashtag_id)) ;

   create table post (
     id integer not null auto_increment,
     date_created datetime,
     user_id integer not null,
     primary key (id)) ;

   create table post_photo (
     post_id integer not null,
     photo_id integer not null,
     primary key (post_id, photo_id)) ;

   create table relation (
     user_id integer not null,
     following_id integer not null);

   create table role (
     role_id integer not null auto_increment,
     name varchar(255) not null, primary key (role_id)) ;

   create table role_users (
     role_role_id integer not null,
     users_id integer not null,
     primary key (role_role_id, users_id));

   create table tags (
     id integer not null auto_increment,
     name varchar(255),
     primary key (id)) ;

   create table user (
     id integer not null auto_increment,
     date_created datetime,
     date_updated datetime,
     email varchar(255),
     first_name varchar(255),
     is_active bit not null,
     last_name varchar(255),
     last_ip varchar(255),
     password varchar(255),
     username varchar(255),
     primary key (id));

   create table user_authority (
     user_id integer not null,
     authority_id bigint not null);

   create table user_group (
     id integer not null auto_increment,
     dated_created datetime,
     is_active bit not null,
     group_id integer not null,
     user_id integer not null,
     primary key (id));


 alter table photo_likes add constraint UK_os48ku1yyqy81por1rq8jt10j unique (likes_id);
 alter table post_photo add constraint UK_heq5be4uv3aiwdwa97927axl5 unique (photo_id);
 alter table comment add constraint FK8kcum44fvpupyw6f5baccx25c foreign key (user_id) references user (id);
 alter table likes add constraint FKb4fqlkvb9mv0gb8kivpxt46uj foreign key (photo_id) references photo (id);
 alter table likes add constraint FKi2wo4dyk4rok7v4kak8sgkwx0 foreign key (user_id) references user (id);
 alter table message add constraint FKt4gjpvxlwdkb0tonr9pd5ftw1 foreign key (parent_message_id) references message (id);
 alter table message add constraint FKb3y6etti1cfougkdr0qiiemgv foreign key (user_id) references user (id);
 alter table message_recipient add constraint FKdw3i7xc9tgt3nwkn5v8pe7tew foreign key (message_id) references message (id);
 alter table message_recipient add constraint FKr7ufkif2qrusc0pf5rk1qgqq9 foreign key (recipient_id) references user (id);
 alter table message_recipient add constraint FKmdi6e25o0p1tptkyk1q8u95b5 foreign key (recipient_group_id) references user_group (id);
 alter table password_reset_token add constraint FK5lwtbncug84d4ero33v3cfxvl foreign key (user_id) references user (id);;
 alter table photo add constraint FKncp190mcvd1kd21cmnibcm19s foreign key (user_id) references user (id);
 alter table photo_comments add constraint FK39mfa1oo4irj4ok5rlo00b7ai foreign key (comment_id) references comment (id);
 alter table photo_comments add constraint FKhny1p5vl4fkh535ntb93nhq9s foreign key (photo_id) references photo (id);
 alter table photo_likes add constraint FK9v24dckvq1gfm17nx6tpj096q foreign key (likes_id) references likes (id);
 alter table photo_likes add constraint FK6kbwds2lt8sqtk6roi5hxrxdk foreign key (photo_id) references photo (id);
 alter table photo_tags add constraint FK77gleit9wfnqkud7foka5yuc5 foreign key (tags_id) references tags (id);
 alter table photo_tags add constraint FK5kexqb6gbvpefwnj43kmseo81 foreign key (photo_id) references photo (id);
 alter table photos_hashtags add constraint FKcow0nds6x9iqt0o2wajamdin8 foreign key (hashtag_id) references hash_tag (id);
 alter table photos_hashtags add constraint FKnlebf9jg103bwap2v01q3ckn8 foreign key (photo_id) references photo (id);
 alter table post add constraint FK72mt33dhhs48hf9gcqrq4fxte foreign key (user_id) references user (id);
 alter table post_photo add constraint FKjp2i7lcg1uuff0hx68vwll5qj foreign key (photo_id) references photo (id);
 alter table post_photo add constraint FK4gjh8fdbqwtesb4gk2p75sg4v foreign key (post_id) references post (id);
 alter table relation add constraint FKf3f595siy43u44whrpegr2326 foreign key (following_id) references user (id);
 alter table relation add constraint FK97bfdogogn3u2g9f5huo0kf4r foreign key (user_id) references user (id);
 alter table role_users add constraint FKipeyaf3dve9njdrl1t23ndidv foreign key (users_id) references user (id);
 alter table role_users add constraint FKauewkogxvirqkiometn9oelj9 foreign key (role_role_id) references role (role_id);
 alter table user_authority add constraint FKgvxjs381k6f48d5d2yi11uh89 foreign key (authority_id) references authority (id);
 alter table user_authority add constraint FKpqlsjpkybgos9w2svcri7j8xy foreign key (user_id) references user (id);
 alter table user_group add constraint FKbegtgnl3oq004958pisko4fu4 foreign key (group_id) references groups (id);
 alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id);
