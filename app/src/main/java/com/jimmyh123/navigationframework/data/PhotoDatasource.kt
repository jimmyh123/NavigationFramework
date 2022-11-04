package com.jimmyh123.navigationframework.data

import com.jimmyh123.navigationframework.R
import com.jimmyh123.navigationframework.model.PhotoDetail

class PhotoDatasource() {
    fun loadPhotos(): List<PhotoDetail>{
        return listOf<PhotoDetail>(
            PhotoDetail(R.string.photo_info_maldives, R.drawable.pexels_asad_photo_maldives_240526),
            PhotoDetail(R.string.beach_with_palm_tree,R.drawable.pexels_asad_photo_maldives_457882),
            PhotoDetail(R.string.beach_with_rocks,R.drawable.pexels_fabian_wiktor_994605),
            PhotoDetail(R.string.bar_on_the_water,R.drawable.pexels_pixabay_237272),
            PhotoDetail(R.string.beach_with_waves,R.drawable.pexels_travis_rupert_1032650),

            PhotoDetail(R.string.cat_hiding_face,R.drawable.pexels_henda_watani_320014),
            PhotoDetail(R.string.green_eyed_cat,R.drawable.pexels_kelvin_valerio_617278),
            PhotoDetail(R.string.group_of_cats,R.drawable.pexels_pixabay_45170),
            PhotoDetail(R.string.white_cat,R.drawable.pexels_pixabay_45201),

            PhotoDetail(R.string.pair_of_dogs,R.drawable.pexels_chevanon_photography_1108099),
            PhotoDetail(R.string.dog_in_forest,R.drawable.pexels_johann_1254140),
            PhotoDetail(R.string.close_up_dog,R.drawable.pexels_lumn_406014),
            PhotoDetail(R.string.dog_by_water,R.drawable.pexels_muhannad_alatawi_58997),
            PhotoDetail(R.string.dog_in_cup,R.drawable.pexels_pixabay_39317),
            PhotoDetail(R.string.dog_on_rug,R.drawable.pexels_valeria_boltneva_1805164),
        )
    }
}