package com.example.dacs3.view.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dacs3.R


//@Composable
//fun AccountDetail() {
//    ScaffoldHome()(
//        scaffoldContent = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding()
//            ) {
//                Column(
//                    modifier = Modifier
//                        .weight(0.3f)
//                        .fillMaxSize(),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Box {
//                        Image(
//                            painter = painterResource(R.drawable.chungtacuatuonglai_mtp),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .fillMaxSize(),
//                            contentScale = ContentScale.Crop,
//                        )
//                        Row(
//                            modifier = Modifier.padding(top = 60.dp, start = 12.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                        ) {
//                            Image(
//                                painter = painterResource(R.drawable.chungtacuatuonglai_mtp),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(150.dp)
//                                    .clip(RoundedCornerShape(100)),
//                                contentScale = ContentScale.Crop,
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                            Column {
//                                Text(
//                                    text = "Khang Phan",
//                                    fontSize = 32.sp,
//                                    fontWeight = FontWeight.Bold,
//                                )
//                                Text(
//                                    text = "0 người theo dõi - Đang theo dõi 5",
//                                    color = Color(0xff808080)
//                                )
//                            }
//
//                        }
//                    }
//                }
//                Column(
//                    modifier = Modifier
//                        .weight(0.7f)
//                        .fillMaxSize()
//                        .background(Color.Black),
//                ) {
//                    Column(
//                        modifier = Modifier.padding(12.dp)
//                    ) {
//                        Text(
//                            text = "Danh sach yêu thích",
//                            fontSize = 22.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White
//                        )
//                        Spacer(modifier = Modifier.height(12.dp))
//
////                    Lazy Column
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.chungtacuatuonglai_mtp),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(55.dp)
//                                    .clip(
//                                        RoundedCornerShape(5.dp)
//                                    ),
//                                contentScale = ContentScale.Crop
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                            Column {
//                                Text(text = "Tên danh sách", color = Color.White)
//                                Text(text = "Số bài hát", color = Color(0xff808080))
//                            }
//                        }
//                        Spacer(modifier = Modifier.height(22.dp))
//
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.chungtacuatuonglai_mtp),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(55.dp)
//                                    .clip(
//                                        RoundedCornerShape(5.dp)
//                                    ),
//                                contentScale = ContentScale.Crop
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                            Column {
//                                Text(text = "Tên danh sách", color = Color.White)
//                                Text(text = "Số bài hát", color = Color(0xff808080))
//                            }
//                        }
//                    }
//                }
//
//            }
//        }
//    )
//}

//@Preview(showBackground = true)
//@Composable
//private fun t() {
//    AccountDetail()
//}