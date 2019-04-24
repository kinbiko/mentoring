# Installing Linux on a virtual machine

> If you don't have more than 2gb of RAM, you're in for a tough time

First and foremost, running a virtual desktop will require a visualiser to run your OS. 
While I'm sure there are others that work just as well, [Oracle VM VirtualBox](https://www.virtualbox.org/wiki/Downloads) does the job just fine.

For the sake of escaping the _peculiarities of Windows_, let's go with Linux.
I'll talk about Bodhi Linux here, but a lot of the basic steps will apply to other OS.

As previously mentioned, I am doing this to avoid using Windows. 
As such, any instructions/keyboard shortcuts will be Windows-specific.

## 1. Download the .iso file

Download Bodhi Linux [here](https://www.bodhilinux.com/download/). 

If your computer is less than 15 years old, the `Standard` version should suffice.

> Standard
> ---
> This is the platform standard for desktop and work station computers created in the last decade. If your processor is capable of running a 64bit operating system, you should be using this release.

## 2. Open the .iso in VirtualBox

Click `NEW` and follow the instructions. This bit is very easy.
As Bodhi is so lightweight, there won't be a huge strain on memory/resources, and in fact, even dedicating as little as 512 MB of RAM and 8 GB of storage should suffice.

N.B. This is **not** the case with other OS. For instance, I ran into problems with Ubuntu MATE as a minimum of 8 GB storage was required.

## 3. Change the resolution

This is not so straightforward.
VirutalBox tends to open your OS in 800x600 as default, so this will need to be changed (unless you're running it on a GameBoy Advance...).

1. Find out the standard resolution of your device
1. Press `Windows` + `Space`
1. Type in `arandr`
1. Press `Enter`
1. Right-click, and select the resolution to match your device.
1. Enter fullscreen in VirtualBox

## 4. Connect to the internet

An OS running in a VM can "tap in" to your computers internet connection directly, regardless of the type of connection you are using. 
To connect, follow these steps:

1. Make sure you are connected to the internet on your device
1. Press `Windows` + `Space`
1. Type in `nm-applet`
1. Press `Enter`
1. Choose `Wired`

That's all there is to it. Now you can have fun playing around with an extremely lightweight, no-frills-attached operating system. 
