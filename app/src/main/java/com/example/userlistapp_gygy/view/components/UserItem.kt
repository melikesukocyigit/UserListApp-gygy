package com.example.userlistapp_gygy.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userlistapp_gygy.data.model.User

@Composable
fun UserItem(user: User) {
    // Tüm içeriği kapsayan dış Kart tasarımı
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Yan yana dizilim için Row kullanıyoruz
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // SOL BÖLÜM: İsmin baş harfini gösteren dairesel Avatar
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                // İsmin ilk harfini alıyoruz (Eğer isim boş değilse)
                val initial = if (user.name.isNotEmpty()) user.name.first().toString() else "?"
                Text(
                    text = initial,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Avatar ile yazılar arasında boşluk

            // SAĞ BÖLÜM: İsim, Email ve Telefonu alt alta dizen Column
            Column {
                Text(
                    text = user.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "✉️ ${user.email}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "📞 ${user.phone}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}