/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.aliucord.gradle

import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class AliucordExtension @Inject constructor(project: Project) {
    val projectType: Property<ProjectType> =
        project.objects.property(ProjectType::class.java).convention(ProjectType.PLUGIN)

    val userCache = project.gradle.gradleUserHomeDir.resolve("caches").resolve("aliucord")

    var discord: DiscordInfo? = null
}

class DiscordInfo(extension: AliucordExtension, val version: Int) {
    val cache = extension.userCache.resolve("discord")

    val apkFile = cache.resolve("discord-$version.apk")
    val jarFile = cache.resolve("discord-$version.jar")
}

fun ExtensionContainer.getAliucord(): AliucordExtension {
    return getByName("aliucord") as AliucordExtension
}